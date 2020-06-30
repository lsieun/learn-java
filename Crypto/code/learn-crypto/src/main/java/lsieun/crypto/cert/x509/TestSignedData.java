package lsieun.crypto.cert.x509;

import lsieun.crypto.cert.asn1.PEMUtils;
import lsieun.crypto.cert.dsa.ASN1DSAUtils;
import lsieun.crypto.cert.dsa.DSAPublicKey;
import lsieun.utils.FileUtils;

public class TestSignedData {
    public static void main(String[] args) {
        String public_key_filepath = FileUtils.getFilePath("cert/dsa/dsa-public.key");
        byte[] public_key_bytes = PEMUtils.read(public_key_filepath);
        DSAPublicKey dsa_public_key = ASN1DSAUtils.parse_public_key(public_key_bytes);
        PublicKeyInfo public_key_info = new PublicKeyInfo(dsa_public_key);

        String signed_data_filepath = FileUtils.getFilePath("cert/dsa/signed_certificate.pem");
        byte[] signed_data_bytes = PEMUtils.read(signed_data_filepath);
        boolean flag = X509Utils.validate_signed_data(signed_data_bytes, public_key_info);
        System.out.println(flag);
    }
}
