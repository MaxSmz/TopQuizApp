package TopQuizSystem.View;

import org.jfree.chart.*;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleInsets;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class ScoreView extends JFrame {
    private JLabel nameTextLabel,totalPointsLabel;
    private JLabel titleLabel,resultNameTextLabel,resultTotalPointsLabel;
    private JButton restartButton,switchButton;
    private JButton quitButton;
    private JPanel topPanel,scorePanel,cards,panel1,panel2;
    private DefaultCategoryDataset dataset_questionType;
    private DefaultPieDataset dataset_questionCategory;
    private CardLayout cardLayout;


    private int score;
    private int fullScore;
    private String firstName,lastName;

    public ScoreView() {
        setTitle("Score");
        setSize(800, 700);
        setLocationRelativeTo(null);

        // Show title
        titleLabel = new JLabel("Congratulations!");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Show score details
        scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(0,2));
        initializeScorePanel();

        // Show Restart and Quit button and title
        topPanel = new JPanel();
        restartButton = new JButton("Restart");
        quitButton = new JButton("Quit");
        topPanel.setLayout(new GridLayout(1, 3, 1,1 ));
        topPanel.add(restartButton);
        topPanel.add(titleLabel);
        topPanel.add(quitButton);
        JPanel chartPanel = new JPanel(new BorderLayout());

        // Used to Switch charts.
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        panel1 = new JPanel();
        panel2 = new JPanel();
        cards.add(panel1,"panel1");
        cards.add(panel2,"panel2");
        switchButton = new JButton("Switch chart type");
        switchButton.setPreferredSize(new Dimension(230, 25));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(switchButton);
        chartPanel.add(cards,BorderLayout.CENTER);
        chartPanel.add(buttonPanel,BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(scorePanel, BorderLayout.CENTER);
        add(chartPanel,BorderLayout.SOUTH);

        setVisible(false);
        setLocationRelativeTo(null);
    }

    /**
     * Initialize Score Panel
     */
    private void initializeScorePanel(){

        nameTextLabel = new JLabel("                            Hello " );
        totalPointsLabel= new JLabel("Your total points are: " );

        nameTextLabel.setFont(new Font("Comic Sans", Font.BOLD, 16));
        totalPointsLabel.setFont(new Font("Comic Sans", Font.BOLD, 16));

        nameTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalPointsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        nameTextLabel.setForeground(Color.BLACK);
        totalPointsLabel.setForeground(Color.BLACK);

        resultNameTextLabel = new JLabel();
        resultTotalPointsLabel = new JLabel(score +"/"+ fullScore);
        resultNameTextLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));
        resultTotalPointsLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));

        resultNameTextLabel.setForeground(Color.RED);
        resultTotalPointsLabel.setForeground(Color.RED);

        scorePanel.add(nameTextLabel);
        scorePanel.add(resultNameTextLabel);
        scorePanel.add(totalPointsLabel);
        scorePanel.add(resultTotalPointsLabel);
    }

    public void updateName() {
        resultNameTextLabel.setText(firstName+" "+lastName);
    }

    /**
     * Create a bar chart and set attributes make it beautiful.
     * @return
     */
    public JFreeChart getChart1() {
        JFreeChart chart = ChartFactory.createBarChart(
                "Your Final Score Distribution", // title
                "Question Type", // X axis title
                "Score", // Y axis title
                dataset_questionType,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        StandardChartTheme theme = (StandardChartTheme)org.jfree.chart.StandardChartTheme.createJFreeTheme();
        theme.setTitlePaint( Color.decode( "#6d6d6d" ) );
        theme.setExtraLargeFont( new Font("Arial",Font.PLAIN, 16) ); //title
        theme.setLargeFont( new Font("Arial",Font.BOLD, 15)); //axis-title
        theme.setRegularFont( new Font("Arial",Font.PLAIN, 11));
        theme.setRangeGridlinePaint( Color.decode("#C0C0C0"));
        theme.setPlotBackgroundPaint( Color.decode("#eeeeee") );
        theme.setChartBackgroundPaint( Color.decode("#eeeeee") );
        theme.setGridBandPaint( Color.red );
        theme.setAxisOffset( new RectangleInsets(0,0,0,0) );
        theme.setBarPainter(new StandardBarPainter());
        theme.setAxisLabelPaint( Color.decode("#666666")  );
        theme.apply( chart );

        chart.getCategoryPlot().setRangeGridlinesVisible(false);
        chart.getCategoryPlot().setRangeGridlineStroke( new BasicStroke() );
        chart.getCategoryPlot().getRangeAxis().setRange(0.0,50.0);
        chart.getCategoryPlot().getRangeAxis().setTickLabelPaint( Color.decode("#666666") );
        chart.getCategoryPlot().getDomainAxis().setTickLabelPaint( Color.decode("#666666") );
        chart.setTextAntiAlias( true );
        chart.setAntiAlias( true );
        chart.getCategoryPlot().getRenderer().setSeriesPaint( 0, Color.decode( "#4572a7" ));
        BarRenderer rend = (BarRenderer) chart.getCategoryPlot().getRenderer();
        CategoryItemLabelGenerator labelGenerator = new StandardCategoryItemLabelGenerator();
        rend.setBaseItemLabelGenerator(labelGenerator);
        rend.setBaseItemLabelsVisible(true);
        rend.setShadowVisible( true );
        rend.setShadowXOffset( 2 );
        rend.setShadowYOffset( 0 );
        rend.setShadowPaint( Color.decode( "#C0C0C0"));
        rend.setMaximumBarWidth( 0.1);

        return chart;
    }

    /**
     * Add bar chart to the panel and add border.
     */
    public void showChart1() {
        JPanel chartP = new ChartPanel(getChart1());
        EtchedBorder etchedBorder = new EtchedBorder(EtchedBorder.RAISED);
        Border marginBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border compoundBorder = BorderFactory.createCompoundBorder(etchedBorder, marginBorder);
        chartP.setBorder(compoundBorder);
        panel1.add(chartP, BorderLayout.SOUTH);
    }

    /**
     * Create a pie chart and set attributes make it beautiful.
     * @return
     */
    public JFreeChart getChart2() {
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Sample Pie Chart",
                dataset_questionCategory,
                true, // legend
                true, // tooltips
                false // URLs
        );
        return pieChart;
    }

    /**
     * Add pie chart to the panel and add border.
     */
    public void showChart2() {
        JPanel chartP = new ChartPanel(getChart2());
        EtchedBorder etchedBorder = new EtchedBorder(EtchedBorder.RAISED);
        Border marginBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border compoundBorder = BorderFactory.createCompoundBorder(etchedBorder, marginBorder);
        chartP.setBorder(compoundBorder);
        panel2.add(chartP, BorderLayout.SOUTH);
    }

    /**
     * Add actionListener for the two button.
     * @param controller
     */
    public void addController(ActionListener controller) {
        restartButton.addActionListener(controller);
        quitButton.addActionListener(controller);
        switchButton.addActionListener(controller);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
    }

    public void setFullScore(int fullScore) {
        this.fullScore = fullScore;
    }

    public void setScore(int score) {
        resultTotalPointsLabel.setText(score +"/"+ fullScore);
        this.score = score;
    }

    public void setDataset_questionType(DefaultCategoryDataset dataset_questionType) {
        this.dataset_questionType = dataset_questionType;
    }

    public void setDataset_questionCategory(DefaultPieDataset dataset_questionCategory) {
        this.dataset_questionCategory = dataset_questionCategory;
    }

    public JButton getRestartButton() {
        return restartButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

    public JPanel getCards() {
        return cards;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JButton getSwitchButton() {
        return switchButton;
    }
}
