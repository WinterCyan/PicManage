package test.test_common;

import java.io.File;

public class DeleteTest {
    public static void main(String[] args){
        File file = new File("/users/wintercyan/desktop/timetable.png");

        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
    }
}
