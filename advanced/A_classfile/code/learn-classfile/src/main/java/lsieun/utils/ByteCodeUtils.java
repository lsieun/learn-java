package lsieun.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lsieun.domain.AccessFlags;
import lsieun.domain.AttributeContainer;
import lsieun.domain.AttributeInfo;
import lsieun.domain.AttributesCount;
import lsieun.domain.ClassInfo;
import lsieun.domain.Common;
import lsieun.domain.ConstantPoolCount;
import lsieun.domain.ConstantPoolInfo;
import lsieun.domain.FieldContainer;
import lsieun.domain.FieldInfo;
import lsieun.domain.FieldsCount;
import lsieun.domain.InterfaceInfo;
import lsieun.domain.InterfacesContainer;
import lsieun.domain.InterfacesCount;
import lsieun.domain.MagicNumber;
import lsieun.domain.MajorVersion;
import lsieun.domain.MemberContainer;
import lsieun.domain.MemberEnum;
import lsieun.domain.MemberInfo;
import lsieun.domain.MethodContainer;
import lsieun.domain.MethodInfo;
import lsieun.domain.MethodsCount;
import lsieun.domain.MinorVersion;
import lsieun.domain.SuperClass;
import lsieun.domain.ThisClass;
import lsieun.domain.constant.ConstantClassInfo;
import lsieun.domain.constant.ConstantCommonInfo;
import lsieun.domain.constant.ConstantDoubleInfo;
import lsieun.domain.constant.ConstantFieldRefInfo;
import lsieun.domain.constant.ConstantFloatInfo;
import lsieun.domain.constant.ConstantIntegerInfo;
import lsieun.domain.constant.ConstantInterfaceMethodRefInfo;
import lsieun.domain.constant.ConstantInvokeDynamicInfo;
import lsieun.domain.constant.ConstantLongInfo;
import lsieun.domain.constant.ConstantMethodHandleInfo;
import lsieun.domain.constant.ConstantMethodRefInfo;
import lsieun.domain.constant.ConstantMethodTypeInfo;
import lsieun.domain.constant.ConstantNameAndTypeInfo;
import lsieun.domain.constant.ConstantStringInfo;
import lsieun.domain.constant.ConstantUtf8Info;

@SuppressWarnings("Duplicates")
public class ByteCodeUtils {
    public static final int CHAR_COUNT_PER_BYTE = 2;

    public static final Map<Integer, Class> int2ClazzMap;

    static {


        int2ClazzMap = new HashMap<Integer, Class>();
        int2ClazzMap.put(Integer.valueOf(1), ConstantUtf8Info.class);
        int2ClazzMap.put(Integer.valueOf(3), ConstantIntegerInfo.class);
        int2ClazzMap.put(Integer.valueOf(4), ConstantFloatInfo.class);
        int2ClazzMap.put(Integer.valueOf(5), ConstantLongInfo.class);
        int2ClazzMap.put(Integer.valueOf(6), ConstantDoubleInfo.class);
        int2ClazzMap.put(Integer.valueOf(7), ConstantClassInfo.class);
        int2ClazzMap.put(Integer.valueOf(8), ConstantStringInfo.class);
        int2ClazzMap.put(Integer.valueOf(9), ConstantFieldRefInfo.class);
        int2ClazzMap.put(Integer.valueOf(10), ConstantMethodRefInfo.class);
        int2ClazzMap.put(Integer.valueOf(11), ConstantInterfaceMethodRefInfo.class);
        int2ClazzMap.put(Integer.valueOf(12), ConstantNameAndTypeInfo.class);
        int2ClazzMap.put(Integer.valueOf(15), ConstantMethodHandleInfo.class);
        int2ClazzMap.put(Integer.valueOf(16), ConstantMethodTypeInfo.class);
        int2ClazzMap.put(Integer.valueOf(18), ConstantInvokeDynamicInfo.class);
    }

    public static void deCode(String hexCodeStr) {
        int nextIndex = 0;

        // MagicNumber
        MagicNumber magicNumber = new MagicNumber();
        magicNumber.setStartIndex(nextIndex);
        doMagicNumber(hexCodeStr, magicNumber);
        nextIndex = magicNumber.getNextIndex();
        System.out.println(magicNumber);

        // MinorVersion
        MinorVersion minorVersion = new MinorVersion();
        minorVersion.setStartIndex(nextIndex);
        doMinorVersion(hexCodeStr, minorVersion);
        nextIndex = minorVersion.getNextIndex();
        System.out.println(minorVersion);

        // MajorVersion
        MajorVersion majorVersion = new MajorVersion();
        majorVersion.setStartIndex(nextIndex);
        doMajorVersion(hexCodeStr, majorVersion);
        nextIndex = majorVersion.getNextIndex();
        System.out.println(majorVersion);

        // ConstantPoolCount
        ConstantPoolCount constantPoolCount = new ConstantPoolCount();
        constantPoolCount.setStartIndex(nextIndex);
        doConstantPoolCount(hexCodeStr, constantPoolCount);
        int count = constantPoolCount.getCount();
        nextIndex = constantPoolCount.getNextIndex();
        System.out.println(constantPoolCount);

        // ConstantPoolInfo
        ConstantPoolInfo constantPoolInfo = new ConstantPoolInfo();
        constantPoolInfo.setStartIndex(nextIndex);
        constantPoolInfo.setCount(count);
        doConstantPool(hexCodeStr, constantPoolInfo);
        nextIndex = constantPoolInfo.getNextIndex();
        fillValue(constantPoolInfo);
        System.out.println(constantPoolInfo);
        List<ConstantCommonInfo> cpList = constantPoolInfo.getList();
        for(int i=0; i<cpList.size(); i++) {
            ConstantCommonInfo item = cpList.get(i);
            System.out.println("\t" + item);
        }

        // AccessFlags
        AccessFlags accessFlags = new AccessFlags();
        accessFlags.setStartIndex(nextIndex);
        doAccessFlags(hexCodeStr, accessFlags);
        nextIndex = accessFlags.getNextIndex();
        System.out.println(accessFlags);

        // ThisClass
        ThisClass thisClass = new ThisClass();
        thisClass.setStartIndex(nextIndex);
        doThisClass(hexCodeStr, thisClass);
        fillThisClassValue(constantPoolInfo, thisClass);
        nextIndex = thisClass.getNextIndex();
        System.out.println(thisClass);

        // SuperClass
        SuperClass superClass = new SuperClass();
        superClass.setStartIndex(nextIndex);
        doSuperClass(hexCodeStr, superClass);
        fillSuperClassValue(constantPoolInfo, superClass);
        nextIndex = superClass.getNextIndex();
        System.out.println(superClass);

        // InterfacesCount
        InterfacesCount interfacesCount = new InterfacesCount();
        interfacesCount.setStartIndex(nextIndex);
        doInterfacesCount(hexCodeStr, interfacesCount);
        int interfacesNum = interfacesCount.getCount();
        nextIndex = interfacesCount.getNextIndex();
        System.out.println(interfacesCount);

        // InterfaceContainer
        InterfacesContainer interfacesContainer = new InterfacesContainer();
        interfacesContainer.setStartIndex(nextIndex);
        interfacesContainer.setCount(interfacesNum);
        doInterfacesContainer(hexCodeStr, interfacesContainer);
        List<InterfaceInfo> interfaces = interfacesContainer.getInterfaces();
        nextIndex = interfacesContainer.getNextIndex();
        System.out.println(interfacesContainer);
        for(int i=0; i<interfaces.size(); i++) {
            InterfaceInfo interfaceInfo = interfaces.get(i);
            fillInterfaceInfoValue(constantPoolInfo, interfaceInfo);
            System.out.println("\t" + interfaceInfo);
        }

        // ClassInfo
//        ClassInfo classInfo = new ClassInfo();
//        classInfo.setStartIndex(nextIndex);
//        doClassInfo(hexCodeStr, classInfo);
//        nextIndex = classInfo.getNextIndex();
//        System.out.println(classInfo);

        // FieldsCount
        FieldsCount fieldsCount = new FieldsCount();
        fieldsCount.setStartIndex(nextIndex);
        doFieldsCount(hexCodeStr, fieldsCount);
        int fieldNum = fieldsCount.getFieldsCount();
        nextIndex = fieldsCount.getNextIndex();
        System.out.println(fieldsCount);

        // FieldContainer
        FieldContainer fieldContainer = new FieldContainer();
        fieldContainer.setStartIndex(nextIndex);
        fieldContainer.setCount(fieldNum);
        doFieldContainer(hexCodeStr, fieldContainer);
        nextIndex = fieldContainer.getNextIndex();
        List<MemberInfo> fieldList = fieldContainer.getList();
        fillMemberInfoValue(constantPoolInfo, fieldList);
        System.out.println(fieldContainer);
        for(int i=0; i<fieldList.size(); i++) {
            MemberInfo item = fieldList.get(i);
            System.out.println("\t" + item);
            AttributeContainer attributeContainer = item.getAttributeContainer();
            if(attributeContainer == null) continue;
            List<AttributeInfo> attributeInfoList = attributeContainer.getList();
            fillAttributeInfoValue(constantPoolInfo, attributeInfoList);
            for(AttributeInfo attr : attributeInfoList) {
                System.out.println("\t\t" + attr);
            }
        }

        // MethodsCount
        MethodsCount methodsCount = new MethodsCount();
        methodsCount.setStartIndex(nextIndex);
        doMethodsCount(hexCodeStr, methodsCount);
        int methodNum = methodsCount.getMethodsCount();
        nextIndex = methodsCount.getNextIndex();
        System.out.println(methodsCount);

        // MethodContainer
        MethodContainer methodContainer = new MethodContainer();
        methodContainer.setStartIndex(nextIndex);
        methodContainer.setCount(methodNum);
        doMethodContainer(hexCodeStr, methodContainer);
        nextIndex = methodContainer.getNextIndex();
        List<MemberInfo> methodList = methodContainer.getList();
        fillMemberInfoValue(constantPoolInfo, methodList);
        System.out.println(methodContainer);
        for(int i=0; i<methodList.size(); i++) {
            MemberInfo item = methodList.get(i);
            System.out.println("\t" + item);
            AttributeContainer attributeContainer = item.getAttributeContainer();
            if(attributeContainer == null) continue;
            List<AttributeInfo> attributeInfoList = attributeContainer.getList();
            fillAttributeInfoValue(constantPoolInfo, attributeInfoList);
            for(AttributeInfo attr : attributeInfoList) {
                System.out.println("\t\t" + attr);
            }
        }


        // AttributesCount
        AttributesCount attributesCount = new AttributesCount();
        attributesCount.setStartIndex(nextIndex);
        doAttributesCount(hexCodeStr, attributesCount);
        int attributesNum = attributesCount.getCount();
        nextIndex = attributesCount.getNextIndex();
        System.out.println(attributesCount);

        // AttributeContainer
        AttributeContainer attributeContainer = new AttributeContainer();
        attributeContainer.setStartIndex(nextIndex);
        attributeContainer.setCount(attributesNum);
        doAttributeContainer(hexCodeStr, attributeContainer);
        nextIndex = attributeContainer.getNextIndex();
        System.out.println(attributeContainer);
        List<AttributeInfo> attributeInfoList = attributeContainer.getList();
        fillAttributeInfoValue(constantPoolInfo, attributeInfoList);
        for(AttributeInfo attr : attributeInfoList) {
            System.out.println("\t" + attr);
        }

        // Final
        System.out.println("length = " + hexCodeStr.length());
        System.out.println("index = " + nextIndex);

        if(nextIndex < hexCodeStr.length()) {
            String remainStr = hexCodeStr.substring(nextIndex);
            System.out.println("remain: " + remainStr);
        }
    }

    public static void doMagicNumber(String hexCodeStr, MagicNumber instance) {
        doCommon(hexCodeStr, instance, MagicNumber.BYTE_COUNT);

        String hexCode = instance.getHexCode();

        String value = format(hexCode, 4, " ");
        instance.setValue(value);
    }

    public static void doMinorVersion(String hexCodeStr, MinorVersion instance) {
        doCommon(hexCodeStr, instance, MinorVersion.BYTE_COUNT);
        String hexCode = instance.getHexCode();

        int num = HexUtils.toInt(hexCode);
        String value = hexCode + "(" + num + ")";
        instance.setValue(value);
    }

    public static void doMajorVersion(String hexCodeStr, MajorVersion instance) {
        doCommon(hexCodeStr, instance, MajorVersion.BYTE_COUNT);
        String hexCode = instance.getHexCode();

        int num = HexUtils.toInt(hexCode);
        int jdkVersion = num - 44;
        String value = "JDK " + jdkVersion + "(" + num + ")";
        instance.setValue(value);
    }

    public static void doConstantPoolCount(String hexCodeStr, ConstantPoolCount instance) {
        doCommon(hexCodeStr, instance, ConstantPoolCount.BYTE_COUNT);
        String hexCode = instance.getHexCode();

        int sum = HexUtils.toInt(hexCode);
        int count = sum - 1;

        instance.setCount(count);
        instance.setValue("Constant Pool Count " + sum + "(" + count + ")");
    }

    public static void doConstantPool(String hexCodeStr, ConstantPoolInfo instance) {
        int startIndex = instance.getStartIndex();
        int count = instance.getCount();
        Map<Integer, ConstantCommonInfo> map = instance.getMap();
        List<ConstantCommonInfo> list = instance.getList();
        int length = 0;

        int constantPoolStartIndex = 1;
        int tagLength = getCharLength(ConstantCommonInfo.TAG_BYTE_COUNT);
        int index = startIndex;

        for(int i=0; i<count; i++) {
            String tagHexCode = getXPart(hexCodeStr, index, tagLength);
            int constantPoolType = HexUtils.toInt(tagHexCode);

            ConstantCommonInfo cpInfo = null;
            String sectionHexCode = null;
            int sectionLength = 0;

            if(constantPoolType == 1) {
                ConstantUtf8Info section = new ConstantUtf8Info();

                sectionLength = getCharLength(ConstantUtf8Info.BYTE_COUNT);
                sectionHexCode = getXPart(hexCodeStr, index, sectionLength);

                int sectionIndex = 0;
                sectionIndex += tagLength;
                int byteLength = getCharLength(ConstantUtf8Info.LENGTH_BYTE_COUNT);
                String byteHexCode = getXPart(sectionHexCode, sectionIndex, byteLength);
                int byteCount = HexUtils.toInt(byteHexCode);

                sectionLength = getCharLength(ConstantUtf8Info.BYTE_COUNT + byteCount);
                sectionHexCode = getXPart(hexCodeStr, index, sectionLength);
                int byteContentLength = getCharLength(byteCount);
                sectionIndex += byteLength;
                String byteContentHexCode = getXPart(sectionHexCode, sectionIndex, byteContentLength);
                //String value = byteContentHexCode + ": " + toUtf8(byteContentHexCode);
                String value = HexUtils.toUtf8(byteContentHexCode);
                section.setValue(value);


                cpInfo = section;
            }
            else if(constantPoolType == 3) {
                ConstantIntegerInfo section = new ConstantIntegerInfo();
                sectionLength = getCharLength(ConstantIntegerInfo.BYTE_COUNT);
                sectionHexCode = getXPart(hexCodeStr, index, sectionLength);

                int sectionIndex = 0;
                sectionIndex += tagLength;
                int bytesLength = getCharLength(ConstantIntegerInfo.BYTES_BYTE_COUNT);
                String bytesHexCode = getXPart(sectionHexCode, sectionIndex, bytesLength);
                int intValue = HexUtils.toInt(bytesHexCode);
                section.setIntValue(intValue);

                cpInfo = section;
            }
            else if(constantPoolType == 4) {
                ConstantFloatInfo section = new ConstantFloatInfo();
                sectionLength = getCharLength(ConstantFloatInfo.BYTE_COUNT);
                sectionHexCode = getXPart(hexCodeStr, index, sectionLength);

                int sectionIndex = 0;
                sectionIndex += tagLength;
                int bytesLength = getCharLength(ConstantFloatInfo.BYTES_BYTE_COUNT);
                String bytesHexCode = getXPart(sectionHexCode, sectionIndex, bytesLength);
                float f = HexUtils.toFloat(bytesHexCode);
                section.setFloatValue(f);
                String value = f + "F";
                section.setValue(value);

                cpInfo = section;
            }
            else if(constantPoolType == 5) {
                ConstantLongInfo section = new ConstantLongInfo();
                sectionLength = getCharLength(ConstantLongInfo.BYTE_COUNT);
                sectionHexCode = getXPart(hexCodeStr, index, sectionLength);

                int sectionIndex = 0;
                sectionIndex += tagLength;
                int bytesLength = getCharLength(ConstantLongInfo.BYTES_BYTE_COUNT);
                String bytesHexCode = getXPart(sectionHexCode, sectionIndex, bytesLength);
                int intValue = HexUtils.toInt(bytesHexCode);
                section.setLongValue(intValue);
                String value = intValue + "L";
                section.setValue(value);

                cpInfo = section;
            }
            else if(constantPoolType == 6) {
                ConstantDoubleInfo section = new ConstantDoubleInfo();
                sectionLength = getCharLength(ConstantDoubleInfo.BYTE_COUNT);
                sectionHexCode = getXPart(hexCodeStr, index, sectionLength);

                int sectionIndex = 0;
                sectionIndex += tagLength;
                int bytesLength = getCharLength(ConstantDoubleInfo.BYTES_BYTE_COUNT);
                String bytesHexCode = getXPart(sectionHexCode, sectionIndex, bytesLength);
                double doubleValue = HexUtils.toDouble(bytesHexCode);
                section.setDoubleValue(doubleValue);
                String value = doubleValue + "";
                section.setValue(value);

                cpInfo = section;
            }
            else if(constantPoolType == 7) {
                ConstantClassInfo section = new ConstantClassInfo();
                sectionLength = getCharLength(ConstantClassInfo.BYTE_COUNT);
                sectionHexCode = getXPart(hexCodeStr, index, sectionLength);

                int sectionIndex = 0;
                sectionIndex += tagLength;
                int classIndexLength = getCharLength(ConstantClassInfo.CLASS_BYTE_COUNT);
                String classIndexHexCode = getXPart(sectionHexCode, sectionIndex, classIndexLength);
                int classIndex = HexUtils.toInt(classIndexHexCode);
                section.setClassIndex(classIndex);

                cpInfo = section;
            }
            else if(constantPoolType == 8) {
                ConstantStringInfo section = new ConstantStringInfo();
                sectionLength = getCharLength(ConstantStringInfo.BYTE_COUNT);
                sectionHexCode = getXPart(hexCodeStr, index, sectionLength);

                int sectionIndex = 0;
                sectionIndex += tagLength;
                int stringIndexLength = getCharLength(ConstantStringInfo.STRING_BYTE_COUNT);
                String stringIndexHexCode = getXPart(sectionHexCode, sectionIndex, stringIndexLength);
                int stringIndex = HexUtils.toInt(stringIndexHexCode);
                section.setStringIndex(stringIndex);

                cpInfo = section;
            }
            else if(constantPoolType == 9) {
                ConstantFieldRefInfo section = new ConstantFieldRefInfo();
                sectionLength = getCharLength(ConstantFieldRefInfo.BYTE_COUNT);
                sectionHexCode = getXPart(hexCodeStr, index, sectionLength);

                int sectionIndex = 0;
                sectionIndex += tagLength;
                int classIndexLength = getCharLength(ConstantFieldRefInfo.CLASS_BYTE_COUNT);
                String classIndexHexCode = getXPart(sectionHexCode, sectionIndex, classIndexLength);
                int classIndex = HexUtils.toInt(classIndexHexCode);
                sectionIndex += classIndexLength;
                int nameAndTypeIndexLength = getCharLength(ConstantFieldRefInfo.NAME_AND_TYPE_BYTE_COUNT);
                String nameAndTypeIndexHexCode = getXPart(sectionHexCode, sectionIndex, classIndexLength);
                int nameAndTypeIndex = HexUtils.toInt(nameAndTypeIndexHexCode);

                section.setClassIndex(classIndex);
                section.setNameAndTypeIndex(nameAndTypeIndex);


                cpInfo = section;
            }
            else if(constantPoolType == 10) {
                ConstantMethodRefInfo section = new ConstantMethodRefInfo();
                sectionLength = getCharLength(ConstantMethodRefInfo.BYTE_COUNT);
                sectionHexCode = getXPart(hexCodeStr, index, sectionLength);

                int sectionIndex = 0;
                sectionIndex += tagLength;
                int classIndexLength = getCharLength(ConstantMethodRefInfo.CLASS_BYTE_COUNT);
                String classIndexHexCode = getXPart(sectionHexCode, sectionIndex, classIndexLength);
                int classIndex = HexUtils.toInt(classIndexHexCode);
                sectionIndex += classIndexLength;
                int nameAndTypeIndexLength = getCharLength(ConstantMethodRefInfo.NAME_AND_TYPE_BYTE_COUNT);
                String nameAndTypeIndexHexCode = getXPart(sectionHexCode, sectionIndex, classIndexLength);
                int nameAndTypeIndex = HexUtils.toInt(nameAndTypeIndexHexCode);

                section.setClassIndex(classIndex);
                section.setNameAndTypeIndex(nameAndTypeIndex);

                cpInfo = section;
            }
            else if(constantPoolType == 11) {
                ConstantInterfaceMethodRefInfo section = new ConstantInterfaceMethodRefInfo();
                sectionLength = getCharLength(ConstantInterfaceMethodRefInfo.BYTE_COUNT);
                sectionHexCode = getXPart(hexCodeStr, index, sectionLength);

                int sectionIndex = 0;
                sectionIndex += tagLength;
                int classIndexLength = getCharLength(ConstantInterfaceMethodRefInfo.CLASS_BYTE_COUNT);
                String classIndexHexCode = getXPart(sectionHexCode, sectionIndex, classIndexLength);
                int classIndex = HexUtils.toInt(classIndexHexCode);
                sectionIndex += classIndexLength;
                int nameAndTypeIndexLength = getCharLength(ConstantInterfaceMethodRefInfo.NAME_AND_TYPE_BYTE_COUNT);
                String nameAndTypeIndexHexCode = getXPart(sectionHexCode, sectionIndex, classIndexLength);
                int nameAndTypeIndex = HexUtils.toInt(nameAndTypeIndexHexCode);

                section.setClassIndex(classIndex);
                section.setNameAndTypeIndex(nameAndTypeIndex);

                cpInfo = section;
            }
            else if(constantPoolType == 12) {
                ConstantNameAndTypeInfo section = new ConstantNameAndTypeInfo();
                sectionLength = getCharLength(ConstantNameAndTypeInfo.BYTE_COUNT);
                sectionHexCode = getXPart(hexCodeStr, index, sectionLength);

                int sectionIndex = 0;
                sectionIndex += tagLength;
                int nameIndexLength = getCharLength(ConstantNameAndTypeInfo.NAME_BYTE_COUNT);
                String nameIndexHexCode = getXPart(sectionHexCode, sectionIndex, nameIndexLength);
                int nameIndex = HexUtils.toInt(nameIndexHexCode);
                sectionIndex += nameIndexLength;
                int descriptorIndexLength = getCharLength(ConstantNameAndTypeInfo.DESCRIPTOR_BYTE_COUNT);
                String descriptorIndexHexCode = getXPart(sectionHexCode, sectionIndex, nameIndexLength);
                int descriptorIndex = HexUtils.toInt(descriptorIndexHexCode);

                section.setNameIndex(nameIndex);
                section.setDescriptorIndex(descriptorIndex);

                cpInfo = section;
            }
            else{
                System.out.println("constantPoolType = " + constantPoolType + " , constantPoolStartIndex = " + constantPoolStartIndex);
                break;
            }

            cpInfo.setHexCode(sectionHexCode);
            cpInfo.setStartIndex(index);
            cpInfo.setLength(sectionLength);

            index += sectionLength;
            length += sectionLength;

            if(cpInfo != null) {
                cpInfo.setTagHex(tagHexCode);
                cpInfo.setTag(constantPoolType);
                cpInfo.setIndex(constantPoolStartIndex);

                list.add(cpInfo);
                map.put(constantPoolStartIndex, cpInfo);

                constantPoolStartIndex++;

                if (constantPoolType == 5 || constantPoolType == 6) {
                    // Long和Double占两个序号
                    i++;
                    constantPoolStartIndex++;
                }
            }
        }

        String hexCode = getXPart(hexCodeStr, startIndex, length);
        instance.setLength(length);
        instance.setHexCode(hexCode);
    }

    public static void doAccessFlags(String hexCodeStr, AccessFlags instance) {
        doCommon(hexCodeStr, instance, ThisClass.BYTE_COUNT);
        String hexCode = instance.getHexCode();

        String accessFlags = getClassAccessFlags(hexCode);
        instance.setValue(accessFlags);
    }

    public static void doThisClass(String hexCodeStr, ThisClass instance) {
        doCommon(hexCodeStr, instance, ThisClass.BYTE_COUNT);
        String hexCode = instance.getHexCode();

        int num = HexUtils.toInt(hexCode);
        instance.setClassIndex(num);
    }

    public static void doSuperClass(String hexCodeStr, SuperClass instance) {
        doCommon(hexCodeStr, instance, ThisClass.BYTE_COUNT);
        String hexCode = instance.getHexCode();

        int num = HexUtils.toInt(hexCode);
        instance.setClassIndex(num);
    }

    public static void doInterfacesCount(String hexCodeStr, InterfacesCount instance) {
        doCommon(hexCodeStr, instance, ThisClass.BYTE_COUNT);
        String hexCode = instance.getHexCode();

        int num = HexUtils.toInt(hexCode);
        instance.setCount(num);
    }

    public static void doInterfacesContainer(String hexCodeStr, InterfacesContainer instance) {
        int count = instance.getCount();
        List<InterfaceInfo> list = instance.getInterfaces();
        int startIndex = instance.getStartIndex();

        doCommon(hexCodeStr, instance, count * InterfaceInfo.BYTE_COUNT);



        for(int i=0; i<count; i++) {
            InterfaceInfo interfaceInfo = new InterfaceInfo();
            interfaceInfo.setStartIndex(startIndex);
            doCommon(hexCodeStr, interfaceInfo, InterfaceInfo.BYTE_COUNT);

            String hexCode = interfaceInfo.getHexCode();
            int num = HexUtils.toInt(hexCode);
            interfaceInfo.setClassIndex(num);

            startIndex = interfaceInfo.getNextIndex();
            list.add(interfaceInfo);
        }
    }

    public static void doClassInfo(String hexCodeStr, ClassInfo instance) {
        int startIndex = instance.getStartIndex();
        int length = getCharLength(ClassInfo.BYTE_COUNT);
        String hexCode = getXPart(hexCodeStr, startIndex, length);

        int index = 0;
        int accessFlagsLength = getCharLength(ClassInfo.ACCESS_FLAGS_BYTE_COUNT);
        String accessFlagsHexCode = getXPart(hexCode, index, accessFlagsLength);
        String accessFlags = getClassAccessFlags(accessFlagsHexCode);

        index += accessFlagsLength;
        int thisClassLength = getCharLength(ClassInfo.THIS_CLASS_BYTE_COUNT);
        String thisClassHexCode = getXPart(hexCode, index, thisClassLength);

        index += thisClassLength;
        int superClassLength = getCharLength(ClassInfo.SUPER_CLASS_BYTE_COUNT);
        String superClassHexCode = getXPart(hexCode, index, superClassLength);

        index += superClassLength;
        int interfacesCountLength = getCharLength(ClassInfo.INTERFACES_COUNT_BYTE_COUNT);
        String interfacesCountHexCode = getXPart(hexCode, index, interfacesCountLength);
        int interfacesCount = HexUtils.toInt(interfacesCountHexCode);

        //重新计算hexCode和length
        length = getCharLength(ClassInfo.BYTE_COUNT + interfacesCount * ClassInfo.INTERFACES_BYTE_COUNT);
        hexCode = getXPart(hexCodeStr, startIndex, length);

        index += interfacesCountLength;
        int interfacesLength = getCharLength(interfacesCount * ClassInfo.INTERFACES_BYTE_COUNT);
        String interfacesHexCode = getXPart(hexCode, index, interfacesLength);

        instance.setAccessFlagsHexCode(accessFlagsHexCode);
        instance.setAccessFlags(accessFlags);
        instance.setThisClassHexCode(thisClassHexCode);
        instance.setSuperClassHexCode(superClassHexCode);
        instance.setInterfacesCountHexCode(interfacesCountHexCode);
        instance.setInterfacesCount(interfacesCount);
        instance.setInterfacesHexCode(interfacesHexCode);

        instance.setHexCode(hexCode);
        instance.setLength(length);
    }

    public static void doFieldsCount(String hexCodeStr, FieldsCount instance) {
        doCommon(hexCodeStr, instance, FieldsCount.BYTE_COUNT);
        String hexCode = instance.getHexCode();

        int num = HexUtils.toInt(hexCode);
        String value = hexCode + "(" + num + ")";

        instance.setValue(value);
        instance.setFieldsCount(num);
    }

    public static void doFieldContainer(String hexCodeStr, FieldContainer instance) {
        doMemberContainer(hexCodeStr, instance, MemberEnum.FIELD);
    }

    public static void doMethodsCount(String hexCodeStr, MethodsCount instance) {
        doCommon(hexCodeStr, instance, MethodsCount.BYTE_COUNT);
        String hexCode = instance.getHexCode();

        int num = HexUtils.toInt(hexCode);
        instance.setMethodsCount(num);
    }

    public static void doMethodContainer(String hexCodeStr, MethodContainer instance) {
        doMemberContainer(hexCodeStr, instance, MemberEnum.METHOD);
    }

    public static void doMemberContainer(String hexCodeStr, MemberContainer instance, MemberEnum type) {
        int startIndex = instance.getStartIndex();
        int count = instance.getCount();
        List<MemberInfo> list = instance.getList();

        int length = 0;

        int memberStartIndex = startIndex;

        for(int i=0; i<count; i++) {

            // access flags: public/private/protected
            int localIndex = memberStartIndex;
            int accessFlagsLength = getCharLength(FieldInfo.ACCESS_FLAGS_BYTE_COUNT);
            String accessFlagsHexCode = getXPart(hexCodeStr, localIndex, accessFlagsLength);
            //String accessFlags = getFieldAccessFlags(accessFlagsHexCode);

            // field name index
            localIndex += accessFlagsLength;
            int nameIndexLength = getCharLength(FieldInfo.NAME_INDEX_BYTE_COUNT);
            String nameIndexHexCode = getXPart(hexCodeStr, localIndex, nameIndexLength);
            int nameIndex = HexUtils.toInt(nameIndexHexCode);

            // field descriptor index
            localIndex += nameIndexLength;
            int descriptorIndexLength = getCharLength(FieldInfo.DESCRIPTOR_INDEX_BYTE_COUNT);
            String descriptorIndexHexCode = getXPart(hexCodeStr, localIndex, descriptorIndexLength);
            int descriptorIndex = HexUtils.toInt(descriptorIndexHexCode);

            // field attribute count
            localIndex += descriptorIndexLength;
            int attributeCountLength = getCharLength(FieldInfo.ATTRIBUTE_COUNT_BYTE_COUNT);
            String attributesCountHexCode = getXPart(hexCodeStr, localIndex, attributeCountLength);
            int attributesCount = HexUtils.toInt(attributesCountHexCode);

            MemberInfo memberInfo = null;
            String accessFlags = null;

            if(type == MemberEnum.MEMBER) {
                memberInfo = new MemberInfo();
            }
            else if(type == MemberEnum.FIELD) {
                memberInfo = new FieldInfo();
                accessFlags = getFieldAccessFlags(accessFlagsHexCode);
            }
            else if(type == MemberEnum.METHOD) {
                memberInfo = new MethodInfo();
                accessFlags = getMethodAccessFlags(accessFlagsHexCode);
            }
            else{
                System.out.println("ERROR");
                continue;
            }

            int sectionLength = getCharLength(MemberInfo.BYTE_COUNT);
            if(attributesCount > 0) {
                localIndex += attributeCountLength;

                AttributeContainer attributeContainer = new AttributeContainer();
                memberInfo.setAttributeContainer(attributeContainer);

                attributeContainer.setStartIndex(localIndex);
                attributeContainer.setCount(attributesCount);

                doAttributeContainer(hexCodeStr, attributeContainer);
                sectionLength += attributeContainer.getLength();
            }
            String sectionHexCode = getXPart(hexCodeStr, memberStartIndex, sectionLength);

            memberInfo.setStartIndex(memberStartIndex);
            memberInfo.setLength(sectionLength);
            memberInfo.setHexCode(sectionHexCode);
            memberInfo.setAccessFlagsHexCode(accessFlagsHexCode);
            memberInfo.setAccessFlags(accessFlags);
            memberInfo.setNameIndexHexCode(nameIndexHexCode);
            memberInfo.setNameIndex(nameIndex);
            memberInfo.setDescriptorIndexHexCode(descriptorIndexHexCode);
            memberInfo.setDescriptorIndex(descriptorIndex);
            memberInfo.setAttributesCountHexCode(attributesCountHexCode);
            memberInfo.setAttributesCount(attributesCount);

            list.add(memberInfo);

            memberStartIndex += sectionLength;
            length += sectionLength;
        }

        String hexCode = getXPart(hexCodeStr, startIndex, length);

        instance.setLength(length);
        instance.setHexCode(hexCode);
    }

    public static void doAttributesCount(String hexCodeStr, AttributesCount instance) {
        doCommon(hexCodeStr, instance, AttributesCount.BYTE_COUNT);
        String hexCode = instance.getHexCode();

        int num = HexUtils.toInt(hexCode);
        String value = hexCode + "(" + num + ")";

        instance.setValue(value);
        instance.setCount(num);
    }

    public static void doAttributeContainer(String hexCodeStr, AttributeContainer attributeContainer) {
        int startIndex = attributeContainer.getStartIndex();
        int count = attributeContainer.getCount();
        List<AttributeInfo> attributeInfoList = attributeContainer.getList();

        int length = 0;
        int attributeStartIndex = startIndex;
        for(int i=0; i<count; i++) {
            int localIndex = attributeStartIndex;
            int attributeNameIndexLength = getCharLength(AttributeInfo.ATTRIBUTE_NAME_INDEX_BYTE_COUNT);
            String attributeNameIndexHexCode = getXPart(hexCodeStr, localIndex, attributeNameIndexLength);
            int attributeNameIndex = HexUtils.toInt(attributeNameIndexHexCode);

            localIndex += attributeNameIndexLength;
            int attributeLengthLength = getCharLength(AttributeInfo.ATTRIBUTE_LENGTH_BYTE_COUNT);
            String attributeLengthHexCode = getXPart(hexCodeStr, localIndex,  attributeLengthLength);
            int attributeLength = HexUtils.toInt(attributeLengthHexCode);

            int currentAttrTotallength = attributeNameIndexLength + attributeLengthLength + getCharLength(attributeLength);
            String hexCode = getXPart(hexCodeStr, attributeStartIndex, currentAttrTotallength);

            AttributeInfo attributeInfo = new AttributeInfo();
            attributeInfo.setStartIndex(attributeStartIndex);
            attributeInfo.setLength(currentAttrTotallength);
            attributeInfo.setHexCode(hexCode);

            attributeInfo.setAttributeNameIndexHexCode(attributeNameIndexHexCode);
            attributeInfo.setAttributeNameIndex(attributeNameIndex);
            attributeInfo.setAttributeLengthHexCode(attributeLengthHexCode);
            attributeInfo.setAttributeLength(attributeLength);

            attributeInfoList.add(attributeInfo);
            attributeStartIndex += currentAttrTotallength;
            length += currentAttrTotallength;
        }

        String hexCode = getXPart(hexCodeStr, startIndex, length);

        attributeContainer.setLength(length);
        attributeContainer.setHexCode(hexCode);
    }

    // region Ancillary

    public static void fillValue(ConstantPoolInfo constantPoolInfo) {

        List<ConstantCommonInfo> list = constantPoolInfo.getList();
        Map<Integer, ConstantCommonInfo> map = constantPoolInfo.getMap();

        int size = list.size();
        for(int i=0; i<size; i++) {
            ConstantCommonInfo item = list.get(i);
            int tag = item.getTag();

            if(tag == 1) continue;
            if(tag == 7) { // Class
                ConstantClassInfo current = (ConstantClassInfo) item;
                int classIndex = current.getClassIndex();

                ConstantCommonInfo target = map.get(Integer.valueOf(classIndex));
                String value = target.getValue();
                String className = value.replaceAll("/", ".");

                current.setValue(className);
                continue;
            }
            if(tag == 8) { // String
                ConstantStringInfo current = (ConstantStringInfo) item;
                int stringIndex = current.getStringIndex();

                ConstantCommonInfo target = map.get(Integer.valueOf(stringIndex));
                String value = target.getValue();

                current.setValue(value);
                continue;
            }
            if(tag == 12) { // NameAndType
                ConstantNameAndTypeInfo current = (ConstantNameAndTypeInfo) item;
                int nameIndex = current.getNameIndex();
                int descriptorIndex = current.getDescriptorIndex();

                ConstantCommonInfo targetName = map.get(Integer.valueOf(nameIndex));
                String nameValue = targetName.getValue();
                ConstantCommonInfo descriptorName = map.get(Integer.valueOf(descriptorIndex));
                String descriptorValue = descriptorName.getValue();

                String value = nameValue + ": " + descriptorValue;
                current.setValue(value);
                continue;
            }
        }

        for(int i=0; i<size; i++) {
            ConstantCommonInfo item = list.get(i);
            int tag = item.getTag();

            if(tag == 9) { // FieldRef
                ConstantFieldRefInfo current = (ConstantFieldRefInfo) item;
                int classIndex = current.getClassIndex();
                int nameAndTypeIndex = current.getNameAndTypeIndex();

                ConstantCommonInfo targetClass = map.get(Integer.valueOf(classIndex));
                String className = targetClass.getValue();
                ConstantCommonInfo targetNameAndType = map.get(Integer.valueOf(nameAndTypeIndex));
                String nameAndType = targetNameAndType.getValue();

                String value = className + "." + nameAndType;
                current.setValue(value);
                continue;
            }

            if(tag == 10) { // MethodRef
                ConstantMethodRefInfo current = (ConstantMethodRefInfo) item;
                int classIndex = current.getClassIndex();
                int nameAndTypeIndex = current.getNameAndTypeIndex();

                ConstantCommonInfo targetClass = map.get(Integer.valueOf(classIndex));
                String className = targetClass.getValue();
                ConstantCommonInfo targetNameAndType = map.get(Integer.valueOf(nameAndTypeIndex));
                String nameAndType = targetNameAndType.getValue();

                String value = className + "." + nameAndType;
                current.setValue(value);
                continue;
            }

        }
    }

    public static void fillThisClassValue(ConstantPoolInfo constantPoolInfo, ThisClass instance) {
        Map<Integer, ConstantCommonInfo> map = constantPoolInfo.getMap();
        int classIndex = instance.getClassIndex();

        ConstantCommonInfo constantCommonInfo = map.get(classIndex);
        String value = constantCommonInfo.getValue();

        instance.setValue(value);
    }

    public static void fillSuperClassValue(ConstantPoolInfo constantPoolInfo, SuperClass instance) {
        Map<Integer, ConstantCommonInfo> map = constantPoolInfo.getMap();
        int classIndex = instance.getClassIndex();

        ConstantCommonInfo constantCommonInfo = map.get(classIndex);
        if(constantCommonInfo == null) {
            instance.setValue(null);
            return;
        }

        String value = constantCommonInfo.getValue();
        instance.setValue(value);
    }

    public static void fillInterfaceInfoValue(ConstantPoolInfo constantPoolInfo, InterfaceInfo instance) {
        Map<Integer, ConstantCommonInfo> map = constantPoolInfo.getMap();
        int classIndex = instance.getClassIndex();

        ConstantCommonInfo constantCommonInfo = map.get(classIndex);
        String value = constantCommonInfo.getValue();

        instance.setValue(value);
    }

    public static void fillMemberInfoValue(ConstantPoolInfo constantPoolInfo, List<MemberInfo> list) {
        Map<Integer, ConstantCommonInfo> map = constantPoolInfo.getMap();

        int size = list.size();
        for(int i=0; i<size; i++) {
            MemberInfo current = list.get(i);
            int nameIndex = current.getNameIndex();
            int descriptorIndex = current.getDescriptorIndex();

            ConstantCommonInfo targetName = map.get(Integer.valueOf(nameIndex));
            String nameValue = targetName.getValue();
            ConstantCommonInfo descriptorName = map.get(Integer.valueOf(descriptorIndex));
            String descriptorValue = descriptorName.getValue();

            String value = nameValue + ": " + descriptorValue;

            current.setName(nameValue);
            current.setDescriptor(descriptorValue);
            current.setValue(value);
        }
    }

    public static void fillAttributeInfoValue(ConstantPoolInfo constantPoolInfo, List<AttributeInfo> list) {
        Map<Integer, ConstantCommonInfo> map = constantPoolInfo.getMap();

        int size = list.size();
        for(int i=0; i<size; i++) {
            AttributeInfo current = list.get(i);
            int attributeNameIndex = current.getAttributeNameIndex();

            ConstantCommonInfo target = map.get(Integer.valueOf(attributeNameIndex));
            String value = target.getValue();

            current.setValue(value);
            current.setAttributeName(value);
        }
    }

    public static void doCommon(String hexCodeStr, Common instance, int byteCount) {
        int startIndex = instance.getStartIndex();
        int length = getCharLength(byteCount);
        String hexCode = getXPart(hexCodeStr, startIndex, length);

        instance.setLength(length);
        instance.setHexCode(hexCode);
    }

    public static int getCharLength(int byteCount) {
        return CHAR_COUNT_PER_BYTE * byteCount;
    }

    // endregion

    // region Access Flags

    public static String getClassAccessFlags(String accessFlagsHexCode) {
        byte[] bytes = HexUtils.toBytes(accessFlagsHexCode);
        byte byteHigh = bytes[0];
        byte byteLow = bytes[1];

        List<String> list = new ArrayList<String>();
        if(hasBit(byteHigh, 7)) {
            list.add("ACC_ENUM");
        }
        if(hasBit(byteHigh, 6)) {
            list.add("ACC_ANNOTATION");
        }
        if(hasBit(byteHigh, 5)) {
            list.add("ACC_SYNTHETIC");
        }
        if(hasBit(byteHigh, 4)) {
            list.add("ACC_ABSTRACT");
        }
        if(hasBit(byteHigh, 2)) {
            list.add("ACC_INTERFACE");
        }
        if(hasBit(byteLow, 6)) {
            list.add("ACC_SUPER");
        }
        if(hasBit(byteLow, 5)) {
            list.add("ACC_FINAL");
        }
        if(hasBit(byteLow, 1)) {
            list.add("ACC_PUBLIC");
        }

        String accessFlags = list2str(list, "[", "]", ",");
        return accessFlags;
    }

    public static String getFieldAccessFlags(String accessFlagsHexCode) {
        byte[] bytes = HexUtils.toBytes(accessFlagsHexCode);
        byte byteHigh = bytes[0];
        byte byteLow = bytes[1];

        List<String> list = new ArrayList<String>();
        if(hasBit(byteHigh, 7)) {
            list.add("ACC_ENUM");
        }
        if(hasBit(byteHigh, 5)) {
            list.add("ACC_SYNTHETIC");
        }
        if(hasBit(byteLow, 8)) {
            list.add("ACC_TRANSIENT");
        }
        if(hasBit(byteLow, 7)) {
            list.add("ACC_VOLATILE");
        }
        if(hasBit(byteLow, 5)) {
            list.add("ACC_FINAL");
        }
        if(hasBit(byteLow, 4)) {
            list.add("ACC_STATIC");
        }
        if(hasBit(byteLow, 3)) {
            list.add("ACC_PROTECTED");
        }
        if(hasBit(byteLow, 2)) {
            list.add("ACC_PRIVATE");
        }
        if(hasBit(byteLow, 1)) {
            list.add("ACC_PUBLIC");
        }

        String accessFlags = list2str(list, "[", "]", ",");
        return accessFlags;
    }

    public static String getMethodAccessFlags(String accessFlagsHexCode) {
        byte[] bytes = HexUtils.toBytes(accessFlagsHexCode);
        byte byteHigh = bytes[0];
        byte byteLow = bytes[1];

        List<String> list = new ArrayList<String>();

        if(hasBit(byteHigh, 5)) {
            list.add("ACC_SYNTHETIC");
        }
        if(hasBit(byteHigh, 4)) {
            list.add("ACC_STRICTFP");
        }
        if(hasBit(byteHigh, 3)) {
            list.add("ACC_ABSTRACT");
        }
        if(hasBit(byteHigh, 1)) {
            list.add("ACC_NATIVE");
        }
        if(hasBit(byteLow, 8)) {
            list.add("ACC_VARARGS");
        }
        if(hasBit(byteLow, 7)) {
            list.add("ACC_BRIDGE");
        }
        if(hasBit(byteLow, 6)) {
            list.add("ACC_SYNCHRONIZED");
        }
        if(hasBit(byteLow, 5)) {
            list.add("ACC_FINAL");
        }
        if(hasBit(byteLow, 4)) {
            list.add("ACC_STATIC");
        }
        if(hasBit(byteLow, 3)) {
            list.add("ACC_PROTECTED");
        }
        if(hasBit(byteLow, 2)) {
            list.add("ACC_PRIVATE");
        }
        if(hasBit(byteLow, 1)) {
            list.add("ACC_PUBLIC");
        }

        String accessFlags = list2str(list, "[", "]", ",");
        return accessFlags;
    }

    public static boolean hasBit(byte b, int index) {
        if(index > 8 || index < 0) return false;
        int rightShift = index - 1;
        int shiftValue = b >> rightShift;
        int andValue = shiftValue & 0x01;
        if(andValue == 1) return true;
        return false;
    }

    // endregion

    // region hex2X



    // endregion

    // region String

    public static String format(String hexCode, int size, String separator) {
        if(hexCode == null || hexCode.equals("")) return null;

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(int i=0; i<hexCode.length()-1; i++) {
            char ch = hexCode.charAt(i);
            count++;
            sb.append(ch);
            if(count % size == 0) {
                sb.append(separator);
            }
        }

        char ch = hexCode.charAt(hexCode.length() -1);
        sb.append(ch);
        return sb.toString();
    }

    public static String list2str(List<String> list, String start, String stop, String separator) {
        if(list == null || list.size() < 1) return null;

        StringBuilder sb = new StringBuilder();
        sb.append(start);

        int size = list.size();
        for(int i=0; i<size-1; i++) {
            String item = list.get(i);
            sb.append(item + separator);
        }
        String theLast = list.get(size-1);
        sb.append(theLast);

        sb.append(stop);
        return sb.toString();
    }

    public static String getXPart(String hexCodeStr, int startIndex, int length) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) {
            int index = startIndex + i;
            char ch = hexCodeStr.charAt(index);
            sb.append(ch);
        }
        return sb.toString();
    }

    // endregion

}
