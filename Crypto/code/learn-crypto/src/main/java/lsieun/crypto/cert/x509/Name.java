package lsieun.crypto.cert.x509;

public class Name {
    public final String CountryName;
    public final String StateOrProvinceName;
    public final String LocalityName;
    public final String OrganizationName;
    public final String OrganizationUnitName;
    public final String CommonName;
    public final String emailAddress;

    public Name(String countryName,
                String stateOrProvinceName,
                String localityName,
                String organizationName,
                String organizationUnitName,
                String commonName,
                String emailAddress) {
        this.CountryName = countryName;
        this.StateOrProvinceName = stateOrProvinceName;
        this.LocalityName = localityName;
        this.OrganizationName = organizationName;
        this.OrganizationUnitName = organizationUnitName;
        this.CommonName = commonName;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Name{" +
                "CountryName='" + CountryName + "'" +
                ", StateOrProvinceName='" + StateOrProvinceName + "'" +
                ", LocalityName='" + LocalityName + "'" +
                ", OrganizationName='" + OrganizationName + "'" +
                ", OrganizationUnitName='" + OrganizationUnitName + "'" +
                ", CommonName='" + CommonName + "'" +
                ", emailAddress='" + emailAddress + "'" +
                '}';
    }
}
