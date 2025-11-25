This project started as a simple idea to build an interactive CV builder using JavaFX. I wanted users 
to be able to enter their details , preview a professional-looking CV, and print it. 

I kept things modular from the start. Inside com.example.demo1, I created separate controller for each 
screen.
HomeController.java: This was my starting point. I designed a minimal home screen (home.fxml) with a 
"Create New CV" button. I wanted it to feel intuitive.

CreateCVController.java:This handled the form logic. In create-cv.fxml , I used a GridPane to neatly 
align all the input fields-name,email,phone, education,skills etc. I added validation to ensure users
filled in everything before generating the CV.

PreviewController.java: Once the user press "Generate CV", this controller took cover. I used
preview.fxml to lay put the CV using VBox and styled it.

CVBuilderApp.java: This was my main application class. It handled stage switching and scene loading.

Launcher.java: I used this as the entry point to launch the app,keeping it separate from the main
logic for flexibility.

module-info.java: I configured my module dependencies here, making sure JavaFx modules were properly
declared.
