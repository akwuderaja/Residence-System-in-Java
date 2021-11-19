public class IOAccess {
    private static InputOutputInterface io;
    public static InputOutputInterface getInstance(){
        if(io==null){
            while (true){
                String options[] = new String[]
                        {
                                "Select to use DialogIO",
                                "Select to use ConsoleIO"
                        };
                DialogIO dialogIO = new DialogIO();
                int value = dialogIO.readChoice(options);
                if(value==0){
                    io = new DialogIO();
                    break;
                }else if(value==1){
                    io = new ConsoleIO();
                    break;
                }
            }
        }
        return io;
    }
    private IOAccess(){
        while (true){
            String options[] = new String[]
                {
                        "Select to use DialogIO",
                        "Select to use ConsoleIO"
                };
            DialogIO dialogIO = new DialogIO();
            int value = dialogIO.readChoice(options);
            if(value==0){
                io = new DialogIO();
                return;
            }else if(value==1){
                io = new ConsoleIO();
                return;
            }
        }
    }

    public static void main(String[] args) {
        IOAccess ioAccess = new IOAccess();
        System.out.println(ioAccess.getInstance().toString());
    }
}
