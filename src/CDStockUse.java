import java.util.Scanner;

public class CDStockUse {

    public static void main(String[] args) {

        String name = null;
        int mode = 0;

        ASCIIService as = new ASCIIService();

        System.out.println(as.createAscii("Music Stock"));
        System.out.println(as.createAscii("Choice mode"));

        Front ft = new Front();

        ft.disp();
        Scanner scanner = new Scanner(System.in);

        while(true) {
        mode = scanner.nextInt();
        scanner.nextLine();

        CDStock cs = new CDStock(); // CDStockクラスのインスタンスを作成

        switch(mode) {
            case 1:
            	System.out.println(as.createAscii("Search"));
                System.out.println("アーティスト名またはアルバム名を入力してください");
                name = scanner.nextLine();
                cs.road();
                cs.search(name);
                break;
            case 2:
            	System.out.println(as.createAscii("Display"));
                cs.road();
                cs.dispall();
                break;
            case 3:
            	System.out.println(as.createAscii("Remove"));
                System.out.println("削除するアーティスト名を入力してください");
                name = scanner.nextLine();
                cs.road();
                cs.remove(name);
                break;
            case 4:
            	System.out.println(as.createAscii("Add"));
            	cs.add();
            default:
            	System.out.println("有効な数値ではありません");
            	System.out.println("再入力してください");
            	continue;
        	}
        }
    }
}

