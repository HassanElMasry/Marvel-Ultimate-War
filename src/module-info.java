module MarvelMilestone3 {
	requires javafx.controls;
	requires java.desktop;
	requires junit;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
}
