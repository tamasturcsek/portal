package org.test;

import com.vaadin.annotations.Theme
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class PortalUI extends UI {

    private BeanFieldGroup<User> fg = new BeanFieldGroup<>(User.class);

    private Label regLabel = new Label("Registration")
    @PropertyId(User.USERNAME)
    private TextField userRegField = new TextField("User")
    @PropertyId(User.PASSWORD)
    private PasswordField passwordRegField = new PasswordField("Password")
    @PropertyId(User.NAME)
    private  TextField nameRegField = new TextField("Name")
    @PropertyId(User.MAIL)
    private TextField mailRegField = new TextField("E-mail")
    private Button btReg = new Button("OK")

    private Label loginLabel = new Label("Login")
    private TextField userLoginField = new TextField("User")
    private PasswordField passwordLoginField = new PasswordField("Password")
    private Button btLogin = new Button("OK")

    private String username
    private String password

    @Autowired
    private UserRepo repo;
    private Table table;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setSizeFull();
        HorizontalLayout contentPane = new HorizontalLayout();
        contentPane.setSizeFull()
        contentPane.setMargin(true)
        contentPane.setSpacing(true)
        regLabel.addStyleName(ValoTheme.LABEL_H2)
        FormPane fRegister = new FormPane(regLabel,userRegField, passwordRegField, nameRegField, mailRegField, btReg);
        loginLabel.addStyleName(ValoTheme.LABEL_H2)
        FormPane fLogin = new FormPane(loginLabel,userLoginField,passwordLoginField,btLogin)
        contentPane.addComponent(fLogin)

        contentPane.addComponent(fRegister)
        contentPane.setComponentAlignment(fRegister, Alignment.MIDDLE_CENTER)
        fRegister.setSizeFull();


        btReg.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    fg.commit();
                    repo.save(fg.getItemDataSource().getBean());
                    fg.setItemDataSource(new User());
                    refresh();
                } catch (FieldGroup.CommitException e) {
                    e.printStackTrace();
                }
            }
        })
        btLogin.addClickListener(new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent clickEvent) {
                System.out.println(userLoginField.getValue() + "ezvan")
                def u = repo.findByUsernameAndPassword(userLoginField.getValue(),passwordLoginField.getValue())
                if(u.isEmpty()) {
                    fLogin.addComponent(new Label("NOTOK"))
                }
                else {
                    fLogin.addComponent(new Label(("OK")))
                }
            }
        })

        table = buildUserTable();
        //f.addComponent(table);

        fg.setItemDataSource(new User());
        fg.bindMemberFields(this);
        refresh();
        setContent(contentPane);
        getPage().setTitle("Portal")
        userRegField.setNullRepresentation("")
        mailRegField.setNullRepresentation("")
        passwordRegField.setNullRepresentation("")
        nameRegField.setNullRepresentation("")
    }

    private Table buildUserTable() {
        Table t = new Table("Felhasználók");

        return t;
    }

    private void refresh() {
        table.setContainerDataSource(new BeanItemContainer<User>(User.class, repo.findAll()));
        table.setColumnHeader("name", "Név");
    }
}
