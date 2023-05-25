

	import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

	public class ASCIIService {

	    /**
	     * アスキーアートを作るメソッド.
	     * @param str アスキーアートにしたい文字
	     * @return StringBuilder アスキーアートになった文字
	     */
	    public StringBuilder createAscii(String str) {

	        // 生成するグラフィックのサイズ
	        int height =30;
	        int width = 200;
	        // 描写開始位置
	        int startX = 2;
	        int stratY = 20;
	        // フォントを設定
	        Font font = new Font("Arial", Font.BOLD, 14);
	        StringBuilder sysout = new StringBuilder("\n");

	        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        Graphics graphics = bufferedImage.getGraphics();

	        // テキストをgraphicに変換
	        Graphics2D g = (Graphics2D) graphics;
	        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	        g.setFont(font);
	        g.drawString(str, startX, stratY);

	        // graphicをもとにしてアスキーアートに書き起こし
	        for (int y = 0; y < height; y++) {
	            StringBuilder stringBuilder = new StringBuilder();

	            for (int x = 0; x < width; x++) {
	                stringBuilder.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : "@");
	            }
	            if (stringBuilder.toString().trim().isEmpty()) {
	                continue;
	            }
	            sysout.append("\n" + stringBuilder);
	        }
	        sysout.append("\n");
	        return sysout;
	    }

	}

