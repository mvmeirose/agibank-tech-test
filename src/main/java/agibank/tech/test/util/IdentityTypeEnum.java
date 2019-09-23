package agibank.tech.test.util;

public enum IdentityTypeEnum {
	SALESMAN("001"), CUSTOMER("002"), SALE("003");

	private String id;

	IdentityTypeEnum(String  id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static IdentityTypeEnum findById(String type) {
        for (IdentityTypeEnum t : values()) {
            if (t.getId().equals(type))
                return t;
        }
        return null;
    }
}