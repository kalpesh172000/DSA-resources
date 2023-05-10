import java.io.File;
import java.util.Scanner;

public class Test1
{
    public static void main(String[] args){
        try
        {
            File fin=new File("graphdata\\numPath");//graphdata
            if(!fin.exists())
            {
                System.out.print("\nfile doesn't exit");
                System.exit(0);
            }

            Scanner scanner = new Scanner(fin);
            int [] tall = new int [100];
            int i = 0;
            while(scanner.hasNextInt())
            {
                tall[i++] = scanner.nextInt();
                System.out.print(tall[i-1]);
            }

        }
        catch (Exception e)
        {
            System.out.print("\nerror occured: ");
            e.printStackTrace();
        }
    }
}
