import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CDStock {
	Scanner scanner = new Scanner(System.in);
	int sum = 1;
	private String name;
	ArrayList<String[]> csv = new ArrayList<>();

	public CDStock() {

	}

	public CDStock(String name) {
		this.name = name;
	}

	public void dispname() {
		System.out.println("検索キーワードは"+ name + "です。");
	}

	public void road() {

		//使用するファイルの準備
		File f = new File("sample.csv");
		Scanner sc = null;

		try {
			//Scannerの読み込み対象をファイルとする
			sc = new Scanner(f);
			//CSVファイルから１行読み込む
			while( sc.hasNextLine() ) {
				String line = sc.nextLine();
				//文字列中の","を区切り文字として配列を生成する [1,ハンバーガー,200]
				String[] val = line.split(",");
				csv.add(val);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} finally {
			sc.close();
		}

	}

	public void dispall() {
		int sum = 0;
		System.out.println("現在のデータを全て表示します");
		for( String[] array : csv ) {
			for( String str : array ) {
				System.out.print(str);
				sum += 1;
				if(sum % 3 == 0) {
					System.out.println();
				}else {
					System.out.print(",");
				}
			}
		}

		System.out.println("作業を続けますか？ y/n");
		String yn = scanner.nextLine();
        if( yn.equals("y")) {
        	System.out.println("1:検索モード 2:データ表示モード 3:削除モード 4:追加モード");
        	int mode = scanner.nextInt();
        	scanner.nextLine();
        	switch(mode) {
        	case 1:
        		System.out.println("検索モード");
        		System.out.println("アーティスト名またはアルバム名を入力してください");
            	String rename = scanner.nextLine();
        		search(rename);
        		break;
        	case 2:
        		System.out.println("データ一覧表示モード");
        		System.out.println("データを再度一覧表示します");
        		dispall();
        		break;
        	case 3:
        		System.out.println("データ削除モード");
        		System.out.println("削除するアーティストを入力してください");
            	String rename2 = scanner.nextLine();
        		remove(rename2);
        		break;
        	case 4:
        		System.out.println("データ追加モード");
        		add();
        		break;
        	}
        }else {
        	System.out.println("システムを終了します。");
        	System.exit(0);
        }

	}


	public String getName() {
		return this.name;
	}

	public void search(String name) {
		int num = 2;
		System.out.println("検索キーワードは" + name + "です");
		System.out.println("照合中です....");
		for(String [] list : csv) {
			for(String str : list) {
				num++;
			if(str.equals(name) ) {
				if(str.equals(name) && num % 3 ==0) {
					System.out.println("アーティスト名 : " + str);
					System.out.println("アルバム名 : "+ list[sum]);
					System.out.println("在庫数 : " + list[sum+1]);
					}else if(str.equals(name) && num % 3 == 1) {
						System.out.println("アーティスト名 : " + list[sum-1]);
						System.out.println("アルバム名 : "+ str);
						System.out.println("在庫数 : " + list[sum+1]);
					}
			}else if(!(str.equals(name))&& num == (list.length * csv.size())+1){
				System.out.println("これ以上見つかりません");
                System.out.println("検索を続けますか？ y/n");
                String yn3 = scanner.nextLine();
                if( yn3.equals("y")) {
                	System.out.println("アーティスト名またはアルバム名を入力してください");
                	String rename = scanner.nextLine();
                	//road();
                	search(rename);
                }else {
                	System.out.println("システムを終了します。");
                	System.exit(0);
                }

				}
			}
		}
	}

	public void remove(String name) {
		int num2 = 0;
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("照合中です....");
	    Iterator<String[]> iterator = csv.iterator();
	    while (iterator.hasNext()) {
	        String[] list = iterator.next();
	        for (int i = 0; i < list.length; i += 3) {
	        	num2 += 1;
	            String artist = list[i];
	            String album = list[i + 1];
	            String stock = list[i + 2];
	            if (artist.equals(name)) {
	                System.out.println("該当");
	                System.out.println("アーティスト名: " + artist);
	                System.out.println("アルバム名: " + album);
	                System.out.println("在庫数: " + stock);
	                System.out.println("本当に削除しますか？ y/n");
	                String yn = scanner.nextLine();
	                if (yn.equals("y")) {
	                    iterator.remove(); // イテレータを使用してリストから要素を削除
	                    csv.remove(list); // リストからも要素を削除
	                    updateCSV(); // CSVファイルを更新
	                    System.out.println("削除を続けますか？ y/n");
	                    String yn1 = scanner.nextLine();
	                    if( yn1.equals("y")) {
	                    	System.out.println("アーティスト名を入力してください");
	                    	String rename = scanner.nextLine();
	                    	remove(rename);
	                    }else {
	                    	System.out.println("システムを終了します。");
	                    	System.exit(0);
	                    }
	                } else {
	                    System.out.println("削除を取り消しました");
	                    System.out.println("削除を続けますか？ y/n");
	                    String yn2 = scanner.nextLine();
	                    if( yn2.equals("y")) {
	                    	System.out.println("アーティスト名を入力してください");
	                    	String rename = scanner.nextLine();
	                    	//road();
	                    	remove(rename);
	                    }else {
	                    	System.out.println("システムを終了します。");
	                    	System.exit(0);
	                    }
	                }
	                break; // 内側のループを終了
	            }else if(!artist.equals(name) && num2 == csv.size()){
	            	//System.out.println(num2);
	            	//System.out.println(csv.size());
	            	System.out.println("該当名はありませんでした");
	            	System.exit(0);
	            	}
	            }
	        }
	    }


	private void updateCSV() {
	    try {
	        BufferedWriter writer = new BufferedWriter(new FileWriter("sample.csv"));
	        for (String[] row : csv) {
	            StringBuilder sb = new StringBuilder();
	            for (String cell : row) {
	                sb.append(cell).append(",");
	            }
	            sb.deleteCharAt(sb.length() - 1); // 最後のカンマを削除
	            writer.write(sb.toString());
	            writer.newLine();
	        }
	        writer.close();
	        System.out.println("CSVファイルが正常に更新されました");
	    } catch (IOException e) {
	        System.out.println("CSVファイルの更新中にエラーが発生しました");
	        e.printStackTrace();
	    }
	}

    public void add() {
        System.out.println("追加データを入力してください");
        System.out.println("アーティスト名");
        String artist = scanner.nextLine();
        System.out.println("アルバム名");
        String album = scanner.nextLine();
        System.out.println("在庫数");
        String stock = scanner.nextLine();
        String[] data = { artist, album, stock };
        csv.add(data);
        updateCSV2();
        System.out.println("データがCSVファイルに追加されました");
        System.out.println("追加を続けますか？ y/n");
        String yn = scanner.nextLine();
        if( yn.equals("y")) {
        	add();
        }else {
        	System.out.println("システムを終了します。");
        	System.exit(0);
        }
    }

    private void updateCSV2() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("sample.csv", true));
            String[] lastData = csv.get(csv.size() - 1);
            for (int i = 0; i < lastData.length; i++) {
                writer.write(lastData[i]);
                if (i != lastData.length - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();
            writer.close();
            System.out.println("CSVファイルが正常に更新されました");
        } catch (IOException e) {
            System.out.println("CSVファイルの更新中にエラーが発生しました");
            e.printStackTrace();
        }
    }

    public String disp2() {
    	String [] boxes = {"検索モード","データ表示モード","削除モード","追加モード"};
    	int num = 0;
    	System.out.println("モードを選択してください");
        for(String box : boxes) {
        	num+=1;
        	System.out.println(num + " : " + box);
        }
        String mode = scanner.nextLine();
        return(mode);
	}
}






