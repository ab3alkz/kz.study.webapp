package kz.study.session;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.*;

import static kz.study.jdbc.Jdbc.getTestBd;

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

    private static void createAndShowGUI() {
        JFrame frame = new SemanticGo();

        frame.pack();

        frame.setSize(610, 680);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        frame.getContentPane().add(button);

    }

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

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // String s1 =
        // "Основателем фирмы 'НовоКорп' был Иван Семенович, вторым директором фирмы был Сергей Валентинович, фирма просуществовала от 2009 до 2014 года";
        // String s2 = "Кто был основателем фирмы 'НовоКорп'?";

        String[][] SimWorldBase = { { "день", "будень" },
                { "будень", "будни", }, { "зимний", "зима", } };

        String[] NameBaseS = { "Маша", "Света", "Иван", "Сергей", "Ольга",
                "Александр", "Семен", "Семён", "Валентин" };

        Set<String> setEq = new HashSet<>(); // БАЗА СИНОНИМОВ
        Set<String> EqS = new HashSet<>(); // БАЗА СИНОНИМОВ В ТЕКсТЕ
        Set<String> NameS = new HashSet<>(); // БАЗА ИМЕН
        Set<String> OtchS = new HashSet<>(); // БАЗА ОТЧЕСТВ
        ArrayList<String> simW = new ArrayList<>();
        String s = textArea.getText(); // ВЫДЕРГИВАЕМ ОСНОВНОЙ ТЕКСТ
        String ask = textAreaAsk.getText(); // ВЫДЕРГИВАЕМ ВОПРОС
        s = s.toLowerCase();
        s = s.trim();
        s = s.replaceAll(",", " ");

        ask = ask.toLowerCase();
        ask = ask.trim();

        String[] SplitS = s.split(" "); // СЛОВА ТЕКСТА
        String[] Sask = ask.split(" "); // СЛОВА ВОПРОСА
 
        /*
         * for (int i = 0; i < SplitS.length; i++) { boolean b =
         * setEq.add(SplitS[i]); if (b == false) { simW.add(SplitS[i]); } }
         */

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

        for (String Split1 : SplitS) {
            try {
                for (String NameBase : getTestBd("SELECT * from p_person")) {
                    if (Split1.equalsIgnoreCase(NameBase)) {
                        NameS.add(NameBase);
                    }
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        for (String strNpr : NameS) {
            textAreaT.append("В предложении были найдены следующие имена: "
                    + strNpr + "\n");
        }

        for (String Split : SplitS) {
            for (String strN : NameS) {
                // if (SplitS[g].indexOf(strN) != -1) {
                if (!Split.equalsIgnoreCase(strN)) {
                    if (Split.contains("ич") || Split.contains("ов") || Split.contains("ова")) {
                        // textAreaT.append("В предложении были найдены Отчества: "
                        // + SplitS[g] + "\n");
                        OtchS.add(Split);
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
 
        /*
         * String result = ""; if (simW.size() != 0) {
         * textAreaT.append("В предложении было найдено " + simW.size() +
         * " похожих слова: \n"); for (int i = 0; i < simW.size(); i++) {
         * textAreaT.append("[" + simW.get(i) + "] "); for (int с = 0; с <
         * SimWorldBase.length; ++с) { if
         * (SimWorldBase[с][0].equalsIgnoreCase(simW.get(i))) { result =
         * SimWorldBase[с][1]; textAreaT.append("Синонимы [" + result + "]\n");
         * // Логическая цепочка. if
         * (SimWorldBase[с][0].equalsIgnoreCase(result)) { result =
         * SimWorldBase[с][1]; textAreaT.append("Синонимы к син [" + result +
         * "]\n"); break; } break; } } }
         * 
         * } else {
         * textAreaT.append("[Похожих слов в предложении не найдено]\n"); }
         */

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