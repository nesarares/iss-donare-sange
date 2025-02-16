package donation.client.controllers;

import donation.services.IMainService;
import donation.utils.IObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public abstract class AbstractController implements Initializable, IObserver {

    private LoginController loginController;
    private Stage stageView;
    private ControllerRoot controllerRoot;
    IMainService mainService;
    String username;

    @FXML
    protected void handleLogoutEvent(ActionEvent e) {
        mainService.logout(username, null);
        controllerRoot.getObservers().clear();
        loginController.showLoginWindow();
        stageView.close();
    }

    void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    IMainService getMainService() {
        return mainService;
    }

    String getUsername() {
        return username;
    }

    void setControllerRoot(ControllerRoot controllerRoot) {
        this.controllerRoot = controllerRoot;
    }

    public void setMainService(IMainService mainService, String username, Stage stageView) {
        this.mainService = mainService;
        this.username = username;
        this.stageView = stageView;

        stageView.setOnCloseRequest(ev -> {
            mainService.logout(username, null);
            System.exit(0);
        });
    }

}
