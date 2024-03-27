package Model.dto;

public class CharitableOrganizationDTO extends CredentialsDTO {
    private int charitableOrgId;
    private String charitableOrgName;

    public int getCharitableOrgId() {
        return charitableOrgId;
    }

    public void setCharitableOrgId(int charitableOrgId) {
        this.charitableOrgId = charitableOrgId;
    }

    public String getCharitableOrgName() {
        return charitableOrgName;
    }

    public void setCharitableOrgName(String charitableOrgName) {
        this.charitableOrgName = charitableOrgName;
    }
}
