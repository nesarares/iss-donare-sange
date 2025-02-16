package donation.client;

import donation.client.controllers.LoginController;
import donation.services.IMainService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartClient extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");

            primaryStage.setTitle("Donare de sange");
            primaryStage.getIcons().add(new Image("donation/client/icon.png"));
            primaryStage.setOnCloseRequest((event) -> System.exit(0));
            primaryStage.setResizable(false);

            IMainService service = (IMainService) factory.getBean("service");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./views/LoginView.fxml"));
            Parent root = loader.load();
            LoginController ctrl = loader.getController();
            ctrl.setMainService(service);
            ctrl.loadLoginWindow(root, primaryStage);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("You must start the server first!");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
