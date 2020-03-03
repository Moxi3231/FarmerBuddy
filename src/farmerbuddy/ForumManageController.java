/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerbuddy;

import com.jfoenix.controls.*;
import fb_classes.Answer;
import fb_classes.Category;
import fb_classes.Question;
import fb_classes.User;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author moxan
 */
public class ForumManageController implements Initializable
{

    //@FXML
    //private JFXTreeTableView<?> rootTable;
    /**
     * Initializes the controller class.
     */
    //private boolean tFlag =true;
    private Global gb = Global.getGlobal();
    private DBContext dbCon = DBContext.getDbContext();
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXListView<Label> listView;

    //@FXML
    //private StackPane stackPane;
    //@FXML
    //private JFXToggleButton toogleBtn;
    @FXML
    private JFXButton detailsBtn;

    @FXML
    private JFXButton addBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        loadAllQuestion();
    }

    public void loadAllQuestion()
    {
        try
        {
            int len = listView.getItems().size();
            listView.getItems().remove(0, len);
            Session sess = dbCon.getSession();
            //Transaction tr=  sess.beginTransaction();
            List<Question> questions = sess.createQuery("select q from Question q").list();
            sess.close();
            for (Question q : questions)
            {
                //JFXListCell<Text> ct = new JFXListCell<>();
                //ct.setText(value);
                Label l = new Label();
                l.setId(String.valueOf(q.Q_Id));
                l.setText(q.question);
                //l.setStyle("-fx-padding: 1;");
                listView.getItems().add(l);
            }

        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void loadQuestionByCategory(String cat)
    {
    }

    public void loadQuestions(String ques)
    {
    }

    public void expandQuestion()
    {
        try
        {
            //anchorPane.disableProperty();
            //System.out.println(listView.getSelectionModel().getSelectedItem().getId());
            Label l = listView.getSelectionModel().getSelectedItem();
            if (l == null)
            {
                return;
            }
            JFXDialogLayout content = new JFXDialogLayout();
            StackPane stackPane = new StackPane();
            //stackPane.setPrefSize(900, 550);
            
            content.setHeading(new Text("Question Details"));
            int qid = Integer.valueOf(l.getId()).intValue();
            Session sess = dbCon.getSession();
            Transaction tr = sess.beginTransaction();
            Question q = (Question) sess.get(Question.class, qid);
            if (q == null)
            {
                return;
            }
            
            
            Text temp = new Text("Question: " + q.question  );

            tr.commit();
            sess.close();
            AnchorPane an = new AnchorPane();
            an.setPrefHeight(400);
            an.setPrefWidth(900);
            an.setMinWidth(800.0);
           
            
            JFXListView<HBox> innerList=new JFXListView<HBox>();
            innerList.setPrefSize(800, 385);
            List<Answer> answers = q.getAnswers();
            for(Answer answer:answers)
            {
                Label label1 = new Label(answer.getAnswer());
                JFXToggleButton tbtn = new JFXToggleButton();
                tbtn.setText("Toogle Valid");
                tbtn.setSelected(answer.isValidated);
                if(gb.user == null || gb.getUser().RoleId != 2)
                {
                    tbtn.setDisable(true);
                }
                //tbtn.setMaxHeight(5.0);
                HBox hb = new HBox();
                hb.getChildren().addAll(label1,tbtn);
                innerList.getItems().add(hb);
            }
            an.getChildren().addAll(temp,innerList);
            content.setBody(an);
            JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);

            JFXButton closeBtn = new JFXButton("Return");
            closeBtn.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    dialog.close();
                }
            });
            anchorPane.getChildren().add(stackPane);
            content.setActions( closeBtn);
            AnchorPane.setTopAnchor(stackPane, 20.0);
            AnchorPane.setLeftAnchor(stackPane, 30.0);
            AnchorPane.setTopAnchor(innerList,20.0);
            dialog.show();
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

    /* 
    public void dummyQuestion() {
        int i;
        for (i = 0; i < 50; i++) {
            Question q = new Question();
            q.question = randString();
            Label l = new Label(i + " " + q.question);
            l.setId("question" + i);
            listView.getItems().add(l);

        }
        listView.setExpanded(true);
    }
     */
    public void addQuestion()
    {

        JFXDialogLayout content = new JFXDialogLayout();
        StackPane stackPane = new StackPane();
        stackPane.autosize();

        content.setHeading(new Text("Add Question"));
        //content.setBody(new Label("Question Details"));

        AnchorPane an1 = new AnchorPane();
        an1.setPrefWidth(600);

        JFXTextArea textArea1 = new JFXTextArea();
        //tf.autosize();
        textArea1.setPrefHeight(75);
        textArea1.setPrefWidth(550.0);

        textArea1.setPromptText("Enter Your Question Here");
        an1.getChildren().add(textArea1);
        content.setBody(an1);

        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);

        JFXButton closeBtn = new JFXButton("Return");

        closeBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                dialog.close();
            }
        });
        JFXButton addBtn = new JFXButton("Add Question");
        addBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //Insert Here
                String question = textArea1.getText();
                //System.out.println(question);
                try
                {

                    //if(gb.user==null)
                    Session sess = dbCon.getSession();
                    Transaction tr = sess.beginTransaction();

                    Question q = new Question();
                    q.question = question;
                    q.user = (User) sess.get(User.class, 3);

                    sess.save(q);

                    tr.commit();
                    sess.close();
                } catch (Exception e)
                {
                    System.out.println(e);
                }
                loadAllQuestion();
                dialog.close();
            }
        });

        anchorPane.getChildren().add(stackPane);
        content.setActions(addBtn, closeBtn);
        AnchorPane.setTopAnchor(stackPane, (anchorPane.getHeight()) / 6);
        AnchorPane.setLeftAnchor(stackPane, (anchorPane.getWidth()) / 6);

        dialog.show();
    }

    public String randString()
    {
        byte[] array = new byte[25]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return array.toString();
        //System.out.println(generatedString);
    }

    public void viewDetailsOnClick()
    {
        try
        {
            //anchorPane.disableProperty();
            //System.out.println(listView.getSelectionModel().getSelectedItem().getId());
            Label l = listView.getSelectionModel().getSelectedItem();
            if (l == null)
            {
                return;
            }
            JFXDialogLayout content = new JFXDialogLayout();
            StackPane stackPane = new StackPane();
            stackPane.autosize();

            content.setHeading(new Text("Question Details"));
            int qid = Integer.valueOf(l.getId()).intValue();
            Session sess = dbCon.getSession();
            Transaction tr = sess.beginTransaction();
            Question q = (Question) sess.get(Question.class, qid);
            if (q == null)
            {
                return;
            }
            Answer ans = new Answer();
            Answer myAns = new Answer();
            //System.out.println(q+" \n"+q.answers.toArray().toString());
            //List<Answer> lianswer = q.getAnswers();
            //System.out.println(lianswer);
            //for(Answer aa:lianswer)
            //    System.out.println(aa);
            if (!q.getAnswers().isEmpty())
            {
                ans = q.getAnswers().get(0);
            }
            for (Answer a : q.getAnswers())
            {
                //System.out.println(a);moxank
                if (a.isValidated)
                {
                    ans = a;
                }
                if (a.getUser() != null && gb.getUser() != null && a.getUser().UserId == gb.getUser().UserId)
                {
                    myAns = a;
                }
            }
            String ca = "";
            if (q.getQc() != null)
            {
                List<Category> categories = q.getQc().getCategory();

                int i = 0;
                for (Category c : categories)
                {
                    ca += String.valueOf(i) + " " + c.getCategory() + "\n";
                    i++;
                }

            }
            if (ca == "")
            {
                ca = "No categories defined";
            }
            Text temp = new Text("Question: " + q.question + "\n Category: " + ca + "\nAnswer: " + ans.Answer);

            tr.commit();
            sess.close();
            AnchorPane an = new AnchorPane();
            an.setPrefHeight(400);
            an.setPrefWidth(600);
            JFXTextArea tarea = new JFXTextArea();

            tarea.setPrefHeight(100);
            tarea.setPromptText("Enter Your Answer");
            if (myAns != null)
            {
                tarea.setText(myAns.getAnswer());
            }

            an.getChildren().addAll(temp, tarea);
            JFXButton updateAns = new JFXButton("Submit Answer");
            updateAns.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    if (tarea.getText() == null)
                    {
                        return;
                    }
                    Answer ans12 = new Answer();
                    ans12.Answer = tarea.getText();

                    ans12.isValidated = true;
                    Session sess = dbCon.getSession();
                    ans12.user = (User) sess.get(User.class, 3);
                    ans12.question = (Question) sess.get(Question.class, qid);
                    //q.answers.add(ans12);
                    sess.saveOrUpdate(ans12);
                    sess.close();
                    //System.out.println(tarea.getText());
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            content.setBody(an);
            JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);

            JFXButton closeBtn = new JFXButton("Return");
            closeBtn.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    dialog.close();
                }
            });
            anchorPane.getChildren().add(stackPane);
            content.setActions(updateAns, closeBtn);
            AnchorPane.setTopAnchor(stackPane, 20.0);
            AnchorPane.setLeftAnchor(stackPane, 40.0);
            AnchorPane.setBottomAnchor(tarea, 8.0);
            dialog.show();
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
