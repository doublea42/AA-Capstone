package PawDorableApp.activity.request;

public class CreatePetRequest {
    private final String name;
    private final String kind;
    private final String age;
    private final String gender;
    private final String abailiable;


    public CreatePetRequest(String name, String kind, String age, String gender, String abailiable) {
        this.name = name;
        this.kind = kind;
        this.age = age;
        this.gender = gender;
        this.abailiable = abailiable;
    }
}
