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
 * 		Marcos Vinícius		-	marcosviniciuscl@outlook.com
 * 		Marcus Aldrey		-	marcusaldrey@gmail.com
 *
 * See the original project in: <https://github.com/GustavoHBO/Compiler_EXA869>.
 */
package view;

import controller.Controller;
import exception.FileNotLexicalAnalyzerException;
import exception.FileNotSavedException;
import java.io.File;
import java.io.FileNotFoundException;
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
 * This class allow the user use this compile easily.
 *
 * @author Gustavo Henrique.
 */
public class Compiler extends Application {

    private int qnt = 0; // Threads actual.
    private final int QNTM = 0; // Max of thread running in same time.

    @Override
    public void start(Stage primaryStage) {
        try {
            /*Testes*/
            Controller controller = new Controller("./files/", "code.cd");
            //controller.debugPrintGrammar();
            //controller.debugGrammarFirst("<Program>");
            controller.debugGetFollow("<Condition>");
            //controller.debugAddFirst("<Return Statement>");
            System.out.println("Análise Finalizada");
        } catch (FileNotFoundException ex) {
            System.out.println("");
        }
        System.exit(0);
        /*Testes*/
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

    private void analyzeFilesOnFolder(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {// If a folder will be analyzed.
            for (String fileName : file.list()) {
                while (qnt > QNTM) {
                    //Wait the threads finish to run more.
                }
                if (!fileName.substring(fileName.length() - 4).equals(".lex")) {
                    Runnable r1;
                    r1 = () -> {
                        try {

                            Controller controller = new Controller(filePath, fileName);
                            qnt++;
                            controller.analyzeFile();
                            controller.saveTokens();
                            qnt--;
                            Platform.runLater(() -> {
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Success");
                                alert.setHeaderText(null);
                                alert.setContentText("O arquivo \"" + fileName + "\" foi analizado com sucesso!\nVeja o arquivo de saída \"" + fileName.substring(0, fileName.lastIndexOf(".")) + ".lex\"");
                                alert.showAndWait();
                            });
                        } catch (FileNotSavedException ex) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("ERROR Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("O arquivo de saída não pôde ser escrito");
                            alert.showAndWait();
                        } catch (FileNotLexicalAnalyzerException ex) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("ERROR Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("O arquivo " + fileName + " ainda não foi analizado");
                            alert.showAndWait();
                        } catch (FileNotFoundException ex) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("ERROR Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("O a ser analizado não foi encontrado");
                            alert.showAndWait();
                        }
                    };
                    new Thread(r1).start();
                }
            }
        } else { // If only a file will be anylized.
            String fileName = filePath.substring(filePath.lastIndexOf("/"));
            if (!fileName.substring(fileName.length() - 4).equals(".lex")) {
                Runnable r1;
                r1 = () -> {
                    try {
                        Controller controller = new Controller(filePath.substring(0, filePath.lastIndexOf("/")), fileName);
                        qnt++;
                        controller.analyzeFile();
                        controller.saveTokens();
                        qnt--;
                        Platform.runLater(() -> {
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Success");
                            alert.setHeaderText(null);
                            alert.setContentText("O arquivo \"" + fileName + "\" foi analizado com sucesso!\nVeja o arquivo de saída \"" + fileName.substring(0, fileName.lastIndexOf(".")) + ".lex\"");
                            alert.showAndWait();
                        });
                    } catch (FileNotSavedException ex) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("ERROR Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("O arquivo de saída não pôde ser escrito");
                        alert.showAndWait();
                    } catch (FileNotLexicalAnalyzerException ex) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("ERROR Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("O arquivo " + fileName + " ainda não foi analizado");
                        alert.showAndWait();
                    } catch (FileNotFoundException ex) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("ERROR Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("O a ser analizado não foi encontrado");
                        alert.showAndWait();
                    }
                };
                new Thread(r1).start();
            }
        }
    }
}
