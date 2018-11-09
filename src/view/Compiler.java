/* 
 * Copyright (C) 2018 Gustavo Henrique and Marcus Aldrey
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us:	Gustavo Henrique	-	ghboliveira@hotmail.com
 * 		Marcus Aldrey		-	marcusaldrey@gmail.com
 *
 * See the original project in: <https://github.com/GustavoHBO/Compiler_EXA869>.
 */
package view;

import controller.Controller;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author gustavo
 */
public class Compiler extends Application {
    
    Controller controller = Controller.getInstance();
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Click here to analyse the files on folder files");
        btn.setOnAction((ActionEvent event) -> {
            analyzeFilesOnFolder("./files/");
            //System.exit(0);
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 600, 500);
        
        primaryStage.setTitle("Compiler");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void analyzeFilesOnFolder(String file_path){
        File file = new File(file_path);

        if(file.isDirectory()){
            for (String list : file.list()) {
                if(!list.substring(list.length()-4).equals(".lex")){
                    Runnable r1;
                    r1 = () -> {
                        try {
                            controller.saveFileOutput(controller.analyzeFile(file_path, list), file_path, list);
                            Platform.runLater(() -> {
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Success");
                                alert.setHeaderText(null);
                                alert.setContentText("O arquivo \"" + list + "\" foi analizado com sucesso!\nVeja o arquivo de saída \"" + list.substring(0, list.lastIndexOf(".")) + ".lex\"");
                                alert.showAndWait();
                            });
                        } catch (IOException ex) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("ERROR Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("O Arquivo não pode ser lido ou o arquivo de saída não pode ser escrito");
                            alert.showAndWait();
                        }
                    };
                    new Thread(r1).start();
                }
            }
        }
    }
    
}
