package kz.study.session;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

/*
 * @Author Nachinka!
 * ver 1.2
 */
public class SemanticGo extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private static JButton button;
    private JTextArea textArea;
    private JTextArea textAreaAsk;
    private JTextArea textAreaT;

    private SemanticGo() {
        textArea = new JTextArea(13, 46);
        JScrollPane scrollableTextArea = new JScrollPane(textArea);

        scrollableTextArea
                .setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableTextArea
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.getContentPane().add(scrollableTextArea);

        Font bFontOne = new Font("serif", Font.BOLD, 14);
        textArea.setFont(bFontOne);

        textAreaAsk = new JTextArea(4, 46);
        JScrollPane scrollableTextTA = new JScrollPane(textAreaAsk);

        scrollableTextTA
                .setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableTextTA
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.getContentPane().add(scrollableTextTA);
        textAreaAsk.setFont(bFontOne);

        textAreaT = new JTextArea(13, 46);
        JScrollPane scrollableTextT = new JScrollPane(textAreaT);

        scrollableTextT
                .setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableTextT
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.getContentPane().add(scrollableTextT);
        textAreaT.setFont(bFontOne);
        button = new JButton("Анализ!");
        button.addActionListener(this);

    }

    private static void createAndShowGUI() {
        JFrame frame = new SemanticGo();

        frame.pack();

        frame.setSize(610, 680);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        frame.getContentPane().add(button);

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(SemanticGo::createAndShowGUI);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // String s1 =
        // "Основателем фирмы 'НовоКорп' был Иван Семенович, вторым директором фирмы был Сергей Валентинович, фирма просуществовала от 2009 до 2014 года";
        // String s2 = "Кто был основателем фирмы 'НовоКорп'?";

        String[] NameBaseS = {"Маша", "Света", "Иван", "Сергей", "Ольга",
                "Александр", "Семен", "Семён", "Валентин"};

        Set<String> EqS = new HashSet<>(); // БАЗА СИНОНИМОВ В ТЕКсТЕ
        Set<String> NameS = new HashSet<>(); // БАЗА ИМЕН
        Set<String> OtchS = new HashSet<>(); // БАЗА ОТЧЕСТВ
        String s = textArea.getText(); // ВЫДЕРГИВАЕМ ОСНОВНОЙ ТЕКСТ
        String ask = textAreaAsk.getText(); // ВЫДЕРГИВАЕМ ВОПРОС
        s = s.toLowerCase();
        s = s.trim();
        s = s.replaceAll(",", " ");

        ask = ask.toLowerCase();
        ask = ask.trim();

        String[] SplitS = s.split(" "); // СЛОВА ТЕКСТА
        String[] Sask = ask.split(" "); // СЛОВА ВОПРОСА

        textAreaT.setText("========= Характеристика предложения: =======\n");

        if (s.endsWith("?")) {
            textAreaT.append("[Предложение вопросительное]\n");
        }
        if (s.endsWith("!")) {
            textAreaT.append("[Предложение восклицательное]\n");
        }
        if (s.endsWith(".") || s.endsWith("") || s.endsWith(" ")) {
            textAreaT.append("[Предложение обычное]\n");
        }
        textAreaT.append("[Предложение состоит из " + SplitS.length
                + " слов] \n");

        for (String Split2 : SplitS) {
            for (String NameBase : NameBaseS) {
                if (Split2.equalsIgnoreCase(NameBase)) {
                    NameS.add(NameBase);
                }
            }
        }
        for (String strNpr : NameS) {
            textAreaT.append("В предложении были найдены следующие имена: "
                    + strNpr + "\n");
        }

        for (String Split1 : SplitS) {
            for (String strN : NameS) {
                // if (SplitS[g].indexOf(strN) != -1) {
                if (!Split1.equalsIgnoreCase(strN)) {
                    if (Split1.contains("ич")) {
                        // textAreaT.append("В предложении были найдены Отчества: "
                        // + SplitS[g] + "\n");
                        OtchS.add(Split1);
                    }
                }
            }
        }

        for (String Split : SplitS) {
            for (String aSask : Sask) {
                if (Split.equalsIgnoreCase(aSask)) {
                    textAreaT.append("Ключевые слова предложения: " + Split
                            + "\n");
                    EqS.add(Split);
                }
            }
        }

        textAreaT.append("\n");
        textAreaT.append("Найденные Факты: \n");

        StringBuilder sbB = new StringBuilder();
        String resultFullName = null;

        for (int i = 0; i < SplitS.length; i++) {
            for (String strNpr : NameS) {
                for (String strOth : OtchS) {
                    if (SplitS[i].equalsIgnoreCase(strNpr)) {
                        if (SplitS[i + 1].equalsIgnoreCase(strOth)) {
                            textAreaT.append("Возможно найдено полное имя: "
                                    + SplitS[i] + " " + SplitS[i + 1] + "\n");
                            sbB.append(SplitS[i]);
                            sbB.append(SplitS[i + 1]);
                            resultFullName = sbB.toString();

                        }
                    }
                }
            }
        }
        for (int i = 0; i < SplitS.length; i++) {
            for (String aSask : Sask) {
                for (String strSer : EqS) {
                    for (String strNpr : NameS) {
                        if (SplitS[i].equalsIgnoreCase(aSask)) {
                            if (SplitS[i].equalsIgnoreCase(strSer)) {
                                if (SplitS[i + 1].equalsIgnoreCase(strNpr)) {
                                    textAreaT.append("Факты: " + strSer + " " + SplitS[i] + " " + strNpr + " " + resultFullName + "\n");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}