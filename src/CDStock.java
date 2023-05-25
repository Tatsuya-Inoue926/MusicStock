import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CDStock {

	private String name;
	ArrayList<String[]> csv = new ArrayList<>();
	int sum = 1;
	int num = 2;

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
//

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
	}


	public String getName() {
		return this.name;
	}

	public void search(String name) {
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
	                } else {
	                    System.out.println("削除を取り消しました");
	                    System.exit(0);
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
        Scanner scanner = new Scanner(System.in);
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
}






