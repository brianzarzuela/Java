public class Test {
    public static void main(String[] args){
        if (args.length != 2){
            System.out.println("usage: CopyAll <existing_directory_path><new_directory_path>");
            System.exit(1);
        }

        new CopyAll(args[0], args[1]);
    }
}
