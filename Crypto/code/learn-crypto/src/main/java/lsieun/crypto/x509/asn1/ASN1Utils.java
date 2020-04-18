package lsieun.crypto.x509.asn1;

import lsieun.utils.ByteDashboard;

import java.util.LinkedList;
import java.util.List;

import static lsieun.crypto.x509.asn1.ASN1Const.*;

public class ASN1Utils {
    public static List<ASN1Struct> asn1parse(byte[] bytes) {
        List<ASN1Struct> list = new LinkedList<>();

        ByteDashboard bd = new ByteDashboard(bytes);
        while (bd.hasNext()) {
            byte tag_byte = bd.peek();
            boolean constructed = ((tag_byte & 0x20) == 0x20); // bit 6 of the identifier byte
            int tag_class = (tag_byte & 0xC0) >> 6; // bits 7-8 of the identifier byte
            int tag = (tag_byte & 0x1F); // bits 1-5 of the identifier byte

            int length_byte = (bd.peek(1) & 0xFF);
            int length = 0;

            int header_length = 2; // 1 byte tag + 1 byte length_byte
            if ((length_byte & 0x80) == 0x80) {
                int byte_num = length_byte & 0x7F;
                header_length += byte_num;
                for (int i = 0; i < byte_num; i++) {
                    byte b = bd.peek(2 + i);
                    length = (length << 8) | (b & 0xFF);
                }

            } else {
                length = length_byte;
            }

            byte[] header = bd.nextN(header_length);
            byte[] data = bd.nextN(length);

            ASN1Struct item = new ASN1Struct(tag, constructed, tag_class, length, header, data);
            list.add(item);

            if (constructed) {
                List<ASN1Struct> sub_list = asn1parse(item.data);
                item.children.addAll(sub_list);
            }
        }

        return list;
    }

    public static void asn1show(List<ASN1Struct> list, int depth) {
        for (ASN1Struct item : list) {
            for (int i = 0; i < depth; i++) {
                System.out.print("    ");
            }

            int tag = item.tag;
            int tag_class = item.tag_class;
            boolean constructed = item.constructed;
            int length = item.length;
            byte[] header = item.header;
            byte[] data = item.data;
            List<ASN1Struct> children = item.children;

            switch (tag_class) {
                case ASN1Const.ASN1_CLASS_UNIVERSAL:
                    System.out.print(ASN1Const.tag_names[tag]);
                    break;
                case ASN1Const.ASN1_CLASS_APPLICATION:
                    System.out.print("application");
                    break;
                case ASN1Const.ASN1_CONTEXT_SPECIFIC:
                    System.out.print("context");
                    break;
                case ASN1Const.ASN1_PRIVATE:
                    System.out.print("private");
                    break;
            }
            System.out.print(String.format(" (%d:%d) ", tag, length));

            if (tag_class == ASN1Const.ASN1_CLASS_UNIVERSAL) {
                switch (tag) {
                    case ASN1_BOOLEAN:
                    case ASN1_INTEGER:
                    case ASN1_BIT_STRING:
                    case ASN1_OCTET_STRING:
                    case ASN1_NULL:
                    case ASN1_OBJECT_IDENTIFIER: {
                        for (byte b: header) {
                            System.out.print(String.format("%02X ", (b & 0xFF)));
                        }
                        for (int i = 0; i < length; i++) {
                            System.out.print(String.format("%02X ", (data[i] & 0xFF)));
                        }
                    }
                    break;
                    case ASN1_SEQUENCE:
                    case ASN1_SET:
                    {
                        for (byte b: header) {
                            System.out.print(String.format("%02X ", (b & 0xFF)));
                        }
                    }
                    break;
                    case ASN1_NUMERIC_STRING:
                    case ASN1_PRINTABLE_STRING:
                    case ASN1_TELETEX_STRING:
                    case ASN1_VIDEOTEX_STRING:
                    case ASN1_IA5_STRING:
                    case ASN1_UTC_TIME:
                    case ASN1_GENERALIZED_TIME:
                    case ASN1_GRAPHIC_STRING:
                    case ASN1_VISIBLE_STRING:
                    case ASN1_GENERAL_STRING:
                    case ASN1_UNIVERSAL_STRING:
                    case ASN1_CHARACTER_STRING:
                    case ASN1_BMP_STRING:
                    case ASN1_UTF8_STRING: {
                        for (byte b: header) {
                            System.out.print(String.format("%02X ", (b & 0xFF)));
                        }
                        for (int i = 0; i < length; i++) {
                            System.out.print(String.format("%c", data[i]));
                        }
                    }
                    break;
                    default:
                        break;
                }
            }
            else if (tag_class == ASN1Const.ASN1_CONTEXT_SPECIFIC) {
                for (byte b: header) {
                    System.out.print(String.format("%02X ", (b & 0xFF)));
                }
            }

            System.out.println();
            if (constructed && children.size() > 0) {
                asn1show(children, depth + 1);
            }
        }
    }
}
