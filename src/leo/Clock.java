package leo;
import java.awt.*;
import java.util.Date;
import javax.swing.*;


/**
 * @author Leo
 * @ClassName Clock
 * @DATE 2020/12/4 2:15 下午
 * @Description
 */
public class Clock extends JComponent {
    /**
     *
     */
    private static final long serialVersionUID = -5379472973578609775L;
    private Font f = new Font("微软雅黑", Font.PLAIN,15);
    private Font f2 = new Font("微软雅黑",Font.BOLD,15);
    private JLabel l = new JLabel("当前时间：");
    private JLabel display = new JLabel();
    private JLabel display2 = new JLabel("");
    private int hour = 0;
    private int min = 0;
    private int sec = 0;
    private Date now = new Date();
    private Graphics2D g;
    final double PI = Math.PI;
    private String strTime = "" ;

    @SuppressWarnings("deprecation")
    public Clock(){
        add(l);
        l.setBounds(120, 320, 80, 20);
        l.setFont(f);
        add(display);
        display.setBounds(195, 320, 80, 20);
        display.setFont(f);
        display.setBorder(BorderFactory.createLineBorder(Color.black));
        add(display2);
        display2.setBounds(90, 350, 250, 20);
        display2.setFont(f);
        hour = now.getHours();
        min = now.getMinutes();
        sec = now.getSeconds();
        setVisible(true);
    }

    public void paintComponent(Graphics g1){
        double x,y;
        super.paintComponent(g1);
        g = (Graphics2D) g1;
        //反锯齿开关开
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //画表盘
        g.setPaint(new GradientPaint(5,40,Color.red,15,50,Color.yellow,true));
        g.setStroke( new BasicStroke(3,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL));
        g.drawOval(75, 40, 250, 250);
        g.fillOval(195, 160, 10, 10);
        g.setColor(Color.black);

        //画60个点
        for(int i = 0;i < 60;i++)
        {
            double[] co = new double[2];
            co = paint_Dot(i * 2 * PI / 60);
            x = co[0];
            y = co[1];
            if(i == 0 || i == 15 || i == 30 || i == 45)//画3,6,9,12四个大点
            {
                g.fillOval((int)(x - 5 + 200),(int)(y - 5 + 165),10,10);
            }
            else//其他小点
            {
                g.fillOval((int)(x - 2.5 + 200),(int)(y - 2.5 + 165),3,3);
            }
        }

        //画四个数字
        g.setFont(f2);
        g.drawString("3", 300, 171);
        g.drawString("6", 195, 273);
        g.drawString("9", 91, 171);
        g.drawString("12", 195, 68);  //g.drawString("12", 190, 68);

        //画时针，分针，秒针
        paint_HourPointer(hour*3600 + min*60 + sec,g);//时针走过的秒数
        paint_MinutePointer(min*60 + sec,g);//分针走过的秒数
        paint_SecondPointer(sec,g);//秒针走过的秒数
    }

    public void showUI(){
        new Thread() {
            @SuppressWarnings("deprecation")
            public void run() {
                while (true)
                {
                    now = new Date();
                    hour = now.getHours();
                    min = now.getMinutes();
                    sec = now.getSeconds();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    showTime();
                    repaint();
                }
            }
        }.start();
    }

    public void paint_HourPointer(int second,Graphics2D g){//second表示当前时间的时针相对00:00:00走了多少秒
        double x,y,angle;
        angle = second * PI / 21600;//时针的速度为PI/21600 (rad/s)
        x = 200 + 60 * Math.sin(angle);
        y = 165 - 60 * Math.cos(angle);
        g.setStroke( new BasicStroke(5,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND));
        g.setPaint(new GradientPaint(200,165,Color.red,260,165,Color.blue,true));
        g.drawLine(200, 165, (int)x, (int)y);
    }

    public void paint_MinutePointer(int second,Graphics2D g){//second表示当前时间的分针相对00:00:00走了多少秒
        double x,y,angle;
        angle = second * PI / 1800;//分针的速度为PI/1800 (rad/s)
        x = 200 + 80 * Math.sin(angle);
        y = 165 - 80 * Math.cos(angle);
        g.setStroke( new BasicStroke(3,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND));
        g.setPaint(new GradientPaint(200,165,Color.magenta,280,165,Color.blue,true));
        g.drawLine(200, 165, (int)x, (int)y);
    }

    public void paint_SecondPointer(int second,Graphics2D g){//second表示当前时间的秒针相对00:00:00走了多少秒
        double x,y,x1,y1,x2,y2,x3,y3,angle;
        double cos = 90 / Math.sqrt(8125);//90*90+5*5
        double sin = 5 / Math.sqrt(8125);
        angle = second * PI / 30;//时针的速度为PI/30 (rad/s)
        x = 200 + 95 * Math.sin(angle);
        y = 165 - 95 * Math.cos(angle);
        x1 = 200 + 20 * Math.sin(angle + PI);
        y1 = 165 - 20 * Math.cos(angle + PI);
        x2 = 200 + Math.sqrt(8125)* ( Math.sin(angle)*cos - Math.cos(angle)*sin ); //sin(a-b)
        y2 = 165 - Math.sqrt(8125)* ( Math.cos(angle)*cos + Math.sin(angle)*sin ); //cos(a-b)
        x3 = 200 + Math.sqrt(8125)* ( Math.sin(angle)*cos + Math.cos(angle)*sin ); //sin(a+b)
        y3 = 165 - Math.sqrt(8125)* ( Math.cos(angle)*cos - Math.sin(angle)*sin ); //cos(a+b)
        g.setStroke( new BasicStroke(2,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL));
        g.setPaint(new GradientPaint(180,165,Color.CYAN,295,165,Color.MAGENTA,true));
        g.drawLine((int)x1, (int)y1, (int)x, (int)y);
        g.drawLine((int)x2, (int)y2, (int)x, (int)y);
        g.drawLine((int)x3, (int)y3, (int)x, (int)y);
    }

    public double[] paint_Dot(double angle){
        double[] co = new double[2];
        co[0] = 115 * Math.cos(angle);//横坐标
        co[1] = 115 * Math.sin(angle);//纵坐标
        return co;
    }

    @SuppressWarnings("deprecation")
    private void showTime(){
        String date;
        int hour_temp = hour,min_temp = min,sec_temp = sec;
        sec_temp += 1 ;
        if(sec_temp >= 60)
        {
            sec_temp = 0;
            min_temp += 1 ;
        }
        if(min_temp>=60){
            min_temp=0;
            hour_temp+=1;
        }
        if(hour_temp < 10)
            strTime = "0" + hour_temp + ":";
        else
            strTime = "" + hour_temp + ":";

        if(min_temp < 10)
            strTime = strTime + "0" + min_temp + ":";
        else
            strTime = strTime + "" + min_temp + ":";

        if(sec < 10)
            strTime = strTime + "0" + sec_temp;
        else
            strTime = strTime + "" + sec_temp;
        //在窗体上设置显示时间
        date = " " + (now.getYear()+1900) + "年" + (now.getMonth()+1) + "月" + now.getDate() + "日   " + "星期" ;
        switch (now.getDay()) {
            case 1:
                date += "一";
                break;
            case 2:
                date += "二";
                break;
            case 3:
                date += "三";
                break;
            case 4:
                date += "四";
                break;
            case 5:
                date += "五";
                break;
            case 6:
                date += "六";
                break;
            case 7:
                date += "日";
                break;
        }
        date += "  CST";  //Chinese standard time
        strTime = "  " + strTime;
        display.setText(strTime);
        display2.setText(date);
    }

    public static void main(String args[]){
        Clock c = new Clock();
        c.showUI();
        JFrame f = new JFrame("yunyaniu");
        Image img=Toolkit.getDefaultToolkit().getImage("image/logo小.jpg");//窗口图标
        f.setIconImage(img);
        f.setSize(400,420);
        f.setResizable(false);
        f.add(c, BorderLayout.CENTER);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
