import co.edu.uptc.interfaces.Contract;
import co.edu.uptc.models.ManagerModelClient;
import co.edu.uptc.presenters.Presenter;
import co.edu.uptc.views.MainView;

public class App {
    public static void main(String[] args) throws Exception {

        Contract.View view;
        Contract.Presenter presenter;
        Contract.Model model;

        view = new MainView();
        presenter = new Presenter();
        view.setPresenter(presenter);
        presenter.setView(view);
        model = new ManagerModelClient(presenter.getHost(), presenter.getPort(), presenter.getNickname());
        presenter.setModel(model);
        model.setPresenter(presenter);
        System.out.println("Starting...");
        view.begin();
    }
}
