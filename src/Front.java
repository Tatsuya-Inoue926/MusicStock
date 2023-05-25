public class Front {
	String [] boxes = {"検索モード","データ表示モード","削除モード","追加モード"};
	private int num;
	
	public void disp() {
        System.out.println("モードを選択してください");
        for(String box : boxes) {
        	num+=1;
        	System.out.println(num + " : " + box);
        }
	}

}
