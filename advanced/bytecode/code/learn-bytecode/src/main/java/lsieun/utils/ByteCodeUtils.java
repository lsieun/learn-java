package lsieun.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lsieun.domain.ClassInfo;
import lsieun.domain.Common;
import lsieun.domain.ConstantPoolCount;
import lsieun.domain.ConstantPoolInfo;
import lsieun.domain.FieldContainer;
import lsieun.domain.FieldInfo;
import lsieun.domain.FieldsCount;
import lsieun.domain.MethodsCount;
import lsieun.domain.constant.ConstantCommonInfo;
import lsieun.domain.MagicNumber;
import lsieun.domain.MajorVersion;
import lsieun.domain.MinorVersion;
import lsieun.domain.constant.ConstantClassInfo;
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

public class ByteCodeUtils {
    public static final int CHAR_COUNT_PER_BYTE = 2;
    public static final Map<Character, Integer> hex2IntMap;
    public static final Map<Integer, Class> int2ClazzMap;

    static {
        hex2IntMap = new HashMap<Character, Integer>();
        hex2IntMap.put('0', Integer.valueOf(0));
        hex2IntMap.put('1', Integer.valueOf(1));
        hex2IntMap.put('2', Integer.valueOf(2));
        hex2IntMap.put('3', Integer.valueOf(3));
        hex2IntMap.put('4', Integer.valueOf(4));
        hex2IntMap.put('5', Integer.valueOf(5));
        hex2IntMap.put('6', Integer.valueOf(6));
        hex2IntMap.put('7', Integer.valueOf(7));
        hex2IntMap.put('8', Integer.valueOf(8));
        hex2IntMap.put('9', Integer.valueOf(9));
        hex2IntMap.put('A', Integer.valueOf(10));
        hex2IntMap.put('B', Integer.valueOf(11));
        hex2IntMap.put('C', Integer.valueOf(12));
        hex2IntMap.put('D', Integer.valueOf(13));
        hex2IntMap.put('E', Integer.valueOf(14));
        hex2IntMap.put('F', Integer.valueOf(15));

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
        int index = 0;

        MagicNumber magicNumber = new MagicNumber();
        magicNumber.setStartIndex(index);
        doMagicNumber(hexCodeStr, magicNumber);
        System.out.println(magicNumber);

        index = magicNumber.getNextIndex();

        MinorVersion minorVersion = new MinorVersion();
        minorVersion.setStartIndex(index);
        doMinorVersion(hexCodeStr, minorVersion);
        System.out.println(minorVersion);

        index = minorVersion.getNextIndex();

        MajorVersion majorVersion = new MajorVersion();
        majorVersion.setStartIndex(index);
        doMajorVersion(hexCodeStr, majorVersion);
        System.out.println(majorVersion);

        index = majorVersion.getNextIndex();

        ConstantPoolCount constantPoolCount = new ConstantPoolCount();
        constantPoolCount.setStartIndex(index);
        doConstantPoolCount(hexCodeStr, constantPoolCount);
        int count = constantPoolCount.getCount();
        System.out.println(constantPoolCount);

        index = constantPoolCount.getNextIndex();

        ConstantPoolInfo constantPoolInfo = new ConstantPoolInfo();
        constantPoolInfo.setStartIndex(index);
        constantPoolInfo.setCount(count);
        doConstantPool(hexCodeStr, constantPoolInfo);
        System.out.println(constantPoolInfo);
        List<ConstantCommonInfo> cpList = constantPoolInfo.getList();
        for(int i=0; i<cpList.size(); i++) {
            ConstantCommonInfo item = cpList.get(i);
            System.out.println("\t" + item);
        }

        index = constantPoolInfo.getNextIndex();

        ClassInfo classInfo = new ClassInfo();
        classInfo.setStartIndex(index);
        doClassInfo(hexCodeStr, classInfo);
        System.out.println(classInfo);

        index = classInfo.getNextIndex();

        FieldsCount fieldsCount = new FieldsCount();
        fieldsCount.setStartIndex(index);
        doFieldsCount(hexCodeStr, fieldsCount);
        int fieldNum = fieldsCount.getFieldsCount();
        System.out.println(fieldsCount);

        index = fieldsCount.getNextIndex();

        FieldContainer fieldContainer = new FieldContainer();
        fieldContainer.setStartIndex(index);
        fieldContainer.setFieldsCount(fieldNum);
        doFieldContainer(hexCodeStr, fieldContainer);
        List<FieldInfo> fieldList = fieldContainer.getList();
        System.out.println(fieldContainer);
        for(int i=0; i<fieldList.size(); i++) {
            FieldInfo item = fieldList.get(i);
            System.out.println("\t" + item);
        }

        index = fieldContainer.getNextIndex();

        MethodsCount methodsCount = new MethodsCount();
        methodsCount.setStartIndex(index);
        doMethodsCount(hexCodeStr, methodsCount);
        int methodNum = methodsCount.getMethodsCount();
        System.out.println(methodsCount);

        index = methodsCount.getNextIndex();

        System.out.println("index = " + index);

        String remainStr = hexCodeStr.substring(index);
        System.out.println("remain: " + remainStr);
    }

    public static void doMagicNumber(String hexCodeStr, MagicNumber instance) {
//        int startIndex = instance.getStartIndex();
//        int length = getCharLength(MagicNumber.BYTE_COUNT);
//        String hexCode = getXPart(hexCodeStr, startIndex, length);
//
//        instance.setLength(length);
//        instance.setHexCode(hexCode);
        doCommon(hexCodeStr, instance, MagicNumber.BYTE_COUNT);

        String hexCode = instance.getHexCode();

        String value = format(hexCode, 4, " ");
        instance.setValue(value);
    }

    public static void doMinorVersion(String hexCodeStr, MinorVersion instance) {
        int startIndex = instance.getStartIndex();
        int length = getCharLength(MinorVersion.BYTE_COUNT);
        String hexCode = getXPart(hexCodeStr, startIndex, length);


        int sum = hex2int(hexCode);
        instance.setLength(length);
        instance.setHexCode(hexCode);
        instance.setValue(String.valueOf(sum));
    }

    public static void doMajorVersion(String hexCodeStr, MajorVersion instance) {
        int startIndex = instance.getStartIndex();
        int length = getCharLength(MajorVersion.BYTE_COUNT);
        instance.setLength(length);
        String hexCode = getXPart(hexCodeStr, startIndex, length);
        instance.setHexCode(hexCode);

        int sum = hex2int(hexCode);
        int jdkVersion = sum - 44;
        instance.setValue("JDK " + jdkVersion + "(" + sum + ")");
    }

    public static void doConstantPoolCount(String hexCodeStr, ConstantPoolCount instance) {
        int startIndex = instance.getStartIndex();
        int length = getCharLength(ConstantPoolCount.BYTE_COUNT);
        String hexCode = getXPart(hexCodeStr, startIndex, length);

        int sum = hex2int(hexCode);
        int count = sum - 1;

        instance.setHexCode(hexCode);
        instance.setLength(length);
        instance.setCount(count);
        instance.setValue("Constant Pool Count " + sum + "(" + count + ")");
    }

    // TODO: 交叉引用的值，要合并到value当中
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
            int constantPoolType = hex2int(tagHexCode);

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
                int byteCount = hex2int(byteHexCode);

                sectionLength = getCharLength(ConstantUtf8Info.BYTE_COUNT + byteCount);
                sectionHexCode = getXPart(hexCodeStr, index, sectionLength);
                int byteContentLength = getCharLength(byteCount);
                sectionIndex += byteLength;
                String byteContentHexCode = getXPart(sectionHexCode, sectionIndex, byteContentLength);
                String value = byteContentHexCode + ": " + hex2str(byteContentHexCode);
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
                int classIndex = hex2int(classIndexHexCode);
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
                int stringIndex = hex2int(stringIndexHexCode);
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
                int classIndex = hex2int(classIndexHexCode);
                sectionIndex += classIndexLength;
                int nameAndTypeIndexLength = getCharLength(ConstantFieldRefInfo.NAME_AND_TYPE_BYTE_COUNT);
                String nameAndTypeIndexHexCode = getXPart(sectionHexCode, sectionIndex, classIndexLength);
                int nameAndTypeIndex = hex2int(nameAndTypeIndexHexCode);

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
                int classIndex = hex2int(classIndexHexCode);
                sectionIndex += classIndexLength;
                int nameAndTypeIndexLength = getCharLength(ConstantMethodRefInfo.NAME_AND_TYPE_BYTE_COUNT);
                String nameAndTypeIndexHexCode = getXPart(sectionHexCode, sectionIndex, classIndexLength);
                int nameAndTypeIndex = hex2int(nameAndTypeIndexHexCode);

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
                int nameIndex = hex2int(nameIndexHexCode);
                sectionIndex += nameIndexLength;
                int descriptorIndexLength = getCharLength(ConstantNameAndTypeInfo.DESCRIPTOR_BYTE_COUNT);
                String descriptorIndexHexCode = getXPart(sectionHexCode, sectionIndex, nameIndexLength);
                int descriptorIndex = hex2int(descriptorIndexHexCode);

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
            }
        }

        String hexCode = getXPart(hexCodeStr, startIndex, length);
        instance.setLength(length);
        instance.setHexCode(hexCode);
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
        int interfacesCount = hex2int(interfacesCountHexCode);

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
        int startIndex = instance.getStartIndex();
        int length = getCharLength(FieldsCount.BYTE_COUNT);

        String hexCode = getXPart(hexCodeStr, startIndex, length);
        int sum = hex2int(hexCode);
        String value = hexCode + "(" + sum + ")";

        instance.setLength(length);
        instance.setHexCode(hexCode);
        instance.setValue(value);
        instance.setFieldsCount(sum);
    }

    public static void doFieldContainer(String hexCodeStr, FieldContainer instance) {
        int startIndex = instance.getStartIndex();
        int fieldsCount = instance.getFieldsCount();
        List<FieldInfo> list = instance.getList();

        int length = 0;

        int index = startIndex;

        for(int i=0; i<fieldsCount; i++) {
            int sectionLength = getCharLength(FieldInfo.BYTE_COUNT);
            String sectionHexCode = getXPart(hexCodeStr, index, sectionLength);

            // access flags: public/private/protected
            int sectionIndex = 0;
            int accessFlagsLength = getCharLength(FieldInfo.ACCESS_FLAGS_BYTE_COUNT);
            String accessFlagsHexCode = getXPart(sectionHexCode, sectionIndex, accessFlagsLength);
            String accessFlags = getFieldAccessFlags(accessFlagsHexCode);

            // field name index
            sectionIndex += accessFlagsLength;
            int nameIndexLength = getCharLength(FieldInfo.NAME_INDEX_BYTE_COUNT);
            String nameIndexHexCode = getXPart(sectionHexCode, sectionIndex, nameIndexLength);
            int nameIndex = hex2int(nameIndexHexCode);

            // field descriptor index
            sectionIndex += nameIndexLength;
            int descriptorIndexLength = getCharLength(FieldInfo.DESCRIPTOR_INDEX_BYTE_COUNT);
            String descriptorIndexHexCode = getXPart(sectionHexCode, sectionIndex, descriptorIndexLength);
            int descriptorIndex = hex2int(descriptorIndexHexCode);

            // field attribute count
            sectionIndex += descriptorIndexLength;
            int attributeCountLength = getCharLength(FieldInfo.ATTRIBUTE_COUNT_BYTE_COUNT);
            String attributesCountHexCode = getXPart(sectionHexCode, sectionIndex, attributeCountLength);
            int attributesCount = hex2int(attributesCountHexCode);

            // FIXME: sectionLength，还需要加上attribute的部分

            sectionHexCode = getXPart(hexCodeStr, index, sectionLength);

            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.setStartIndex(index);
            fieldInfo.setLength(sectionLength);
            fieldInfo.setHexCode(sectionHexCode);
            fieldInfo.setAccessFlagsHexCode(accessFlagsHexCode);
            fieldInfo.setAccessFlags(accessFlags);
            fieldInfo.setNameIndexHexCode(nameIndexHexCode);
            fieldInfo.setNameIndex(nameIndex);
            fieldInfo.setDescriptorIndexHexCode(descriptorIndexHexCode);
            fieldInfo.setDescriptorIndex(descriptorIndex);
            fieldInfo.setAttributesCountHexCode(attributesCountHexCode);
            fieldInfo.setAttributesCount(attributesCount);

            list.add(fieldInfo);

            index += sectionLength;
            length += sectionLength;
        }

        String hexCode = getXPart(hexCodeStr, startIndex, length);

        instance.setLength(length);
        instance.setHexCode(hexCode);

    }

    public static void doMethodsCount(String hexCodeStr, MethodsCount instance) {
        doCommon(hexCodeStr, instance, MethodsCount.BYTE_COUNT);

        String hexCode = instance.getHexCode();
        int num = hex2int(hexCode);
        instance.setMethodsCount(num);
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

    public static String getFieldAccessFlags(String accessFlagsHexCode) {
        byte[] bytes = hex2bytes(accessFlagsHexCode);
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

    public static boolean hasBit(byte b, int index) {
        if(index > 8 || index < 0) return false;
        int rightShift = index - 1;
        int shiftValue = b >> rightShift;
        int andValue = shiftValue & 0x01;
        if(andValue == 1) return true;
        return false;
    }

    public static String getClassAccessFlags(String accessFlagsHexCode) {
        char ch0 = accessFlagsHexCode.charAt(0);
        char ch1 = accessFlagsHexCode.charAt(1);
        char ch2 = accessFlagsHexCode.charAt(2);
        char ch3 = accessFlagsHexCode.charAt(3);

        List<String> list = new ArrayList<String>();
        if('1' == ch0) {
            list.add("ACC_SYNTHETIC");
        }
        else if('2' == ch0) {
            list.add("ACC_ANNOTATION");
        }
        else if('4' == ch0) {
            list.add("ACC_ENUM");
        }

        if('2' == ch1) {
            list.add("ACC_INTERFACE");
        }
        else if('4' == ch1) {
            list.add("ACC_ABSTRACT");
        }

        if('1' == ch2) {
            list.add("ACC_FINAL");
        }
        else if('2' == ch2) {
            list.add("ACC_SUPER");
        }

        if('1' == ch3) {
            list.add("ACC_PUBLIC");
        }

        if(list == null || list.size() < 1) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0; i<list.size()-1; i++) {
            String item = list.get(i);
            sb.append(item + ",");
        }

        String theLast = list.get(list.size()-1);
        sb.append(theLast + "]");

        return sb.toString();
    }

    public static String hex2str(String hexCode) {
        hexCode = hexCode.toLowerCase();
        byte[] bytes = hex2bytes(hexCode);
        String str = null;
        try {
            str = new String(bytes, "UTF8");
        } catch (UnsupportedEncodingException e) {
            str = null;
            e.printStackTrace();
        }
        return str;
    }

    public static byte[] hex2bytes(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] array = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(array[pos]) << 4 | toByte(array[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789abcdef".indexOf(c);
        return b;
    }

    public static int hex2int(String hexCode) {
        int base = 16;
        int sum = 0;

        for(int i=0; i<hexCode.length(); i++) {
            char ch = hexCode.charAt(i);
            Integer value = hex2IntMap.get(ch);

            sum = sum * base + value;
        }
        return sum;
    }

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

    public static String getXPart(String hexCodeStr, int startIndex, int length) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) {
            int index = startIndex + i;
            char ch = hexCodeStr.charAt(index);
            sb.append(ch);
        }
        return sb.toString();
    }
}
