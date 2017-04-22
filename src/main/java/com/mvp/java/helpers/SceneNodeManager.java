package com.mvp.java.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class SceneNodeManager {

//    static SceneNodeManager instance =  new SceneNodeManager();
//    private SceneNodeManager() {
//
//    }
//
//    public static SceneNodeManager getInstance() {
//        return instance;
//    }

//    private ConfigurableApplicationContext springContext;
//    FXMLLoader fxmlLoader;


    HashMap<String, Node> mapNameVsSceneNode = new HashMap<String, Node>();

    public Node getSceneNodeFor(String sceneName, boolean reCreatedOne) throws IOException {
        Node scene = mapNameVsSceneNode.get(sceneName);
        if(scene == null || reCreatedOne) {
            scene =  loadSceneNode(sceneName);
            mapNameVsSceneNode.put(sceneName, scene);
        }
        return scene;
    }

    private Node loadSceneNode(String sceneName) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource( AppConstant.VIEWS_FXML_DIR_LOC + sceneName + ".fxml"));
        //fxmlLoader.setControllerFactory(springContext::getBean);
        //Parent parentNode = fxmlLoader.load();

        Parent parentNode = FXMLLoader.load(getClass().getResource( AppConstant.VIEWS_FXML_DIR_LOC + sceneName + ".fxml"));
        return parentNode;
    }

//    public void setSpringContext(ConfigurableApplicationContext springContext)  {
//        this.springContext = springContext;
//    }
//
//    public void setFxmlLoader(FXMLLoader fxmlLoader) {
//        this.fxmlLoader = fxmlLoader;
//    }
}
