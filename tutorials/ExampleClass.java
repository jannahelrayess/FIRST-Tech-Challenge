// Class Declaration
public class ExampleClass {
    // Variable
    String example_string_variable;

    // Constructor Declaration of Class
    public ExampleClass(String example_string_variable) {
        this.example_string_variable = example_string_variable;
    }

    // Getter
    public String getExampleString() {
        return example_string_variable;
    }

    // Setter
    // Uses "void" because it is a keyword used to specify that a 
    // Method doesn't return anything
    public void setExampleString(String new_string) {
        this.example_string_variable = new_string;
    }

    // Method 
    public String ExampleMethod() {
        return ("Hi I am a method and here is what my example string is: " + this.getExampleString());
    }

    // Main method 
    // Always need one to run the program
    // The keyword static means that the method belongs to this class itself
    public static void main(String[] args) {
        ExampleClass obj = new ExampleClass("Hi lol");
        System.out.println(obj.getExampleString());
        obj.setExampleString("PYTHON IS BETTER.");
        System.out.println(obj.getExampleString());
        System.out.println(obj.ExampleMethod());
    }
}



