package application;

import java.io.IOException;
import java.util.ArrayList;

import engine.Game;
import engine.Player;
import engine.PriorityQueue;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;
import model.world.Hero;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Main extends Application implements EventHandler<ActionEvent> {
	Stage window;
	StackPane loadingScreen;
	Button Start;
	Scene loadingScreenScene;
	StackPane Names;
	Label Player1NameLabel;
	Label Player2NameLabel;
	TextField Player1NameInput;
	TextField Player2NameInput;
	HBox Player1NameInputLabel;
	HBox Player2NameInputLabel;
	VBox Player12NameInputLabel;
	Scene NamesInputScene;
	Button ChooseYourChampions;
	StackPane ChoosingChampionsStackPane;
	Scene ChoosingChampionsScene;
	Game game;
	Player Player1;
	Player Player2;
	CheckBox CaptainAmerica1;
	CheckBox Deadpool1;
	CheckBox DrStrange1;
	CheckBox Electro1;
	CheckBox GhostRider1;
	CheckBox Hela1;
	CheckBox Hulk1;
	CheckBox Iceman1;
	CheckBox Ironman1;
	CheckBox Loki1;
	CheckBox Quicksilver1;
	CheckBox Spiderman1;
	CheckBox Thor1;
	CheckBox Venom1;
	CheckBox YellowJacket1;
	CheckBox CaptainAmerica2;
	CheckBox Deadpool2;
	CheckBox DrStrange2;
	CheckBox Electro2;
	CheckBox GhostRider2;
	CheckBox Hela2;
	CheckBox Hulk2;
	CheckBox Iceman2;
	CheckBox Ironman2;
	CheckBox Loki2;
	CheckBox Quicksilver2;
	CheckBox Spiderman2;
	CheckBox Thor2;
	CheckBox Venom2;
	CheckBox YellowJacket2;
	ArrayList<CheckBox> player1CheckBoxes;
	ArrayList<CheckBox> player2CheckBoxes;
	Button ChooseLeaders;
	Button StartFight;
	StackPane LeadersStackPane;
	Scene LeadersScene;
	ArrayList<CheckBox> Team1ChampionsCheckBoxes;
	ArrayList<CheckBox> Team2ChampionsCheckBoxes;
	Scene Playing;
	Label name1Label;
	Label name2Label;
	GridPane Board;
	Label team1Champion1Label;
	Label team1Champion2Label;
	Label team1Champion3Label;
	Label team2Champion1Label;
	Label team2Champion2Label;
	Label team2Champion3Label;
	Label leaderAbilityUsedTeam1Label;
	Label leaderAbilityUsedTeam2Label;
	Button MoveUp;
	Button MoveDown;
	Button MoveLeft;
	Button MoveRight;
	Button EndTurn;
	Button Ability1;
	Button Ability2;
	Button Ability3;
	Button LeaderAbility1;
	Button LeaderAbility2;
	Button AttackUp;
	Button AttackDown;
	Button AttackLeft;
	Button AttackRight;
	Button board00;
	Button board01;
	Button board02;
	Button board03;
	Button board04;
	Button board10;
	Button board11;
	Button board12;
	Button board13;
	Button board14;
	Button board20;
	Button board21;
	Button board22;
	Button board23;
	Button board24;
	Button board30;
	Button board31;
	Button board32;
	Button board33;
	Button board34;
	Button board40;
	Button board41;
	Button board42;
	Button board43;
	Button board44;
	CheckBox AbilityUp;
	CheckBox AbilityDown;
	CheckBox AbilityLeft;
	CheckBox AbilityRight;
	ArrayList<CheckBox> AbilityDirections;
	ChoiceBox<String> target;
	public void start(Stage primaryStage) {
		window = primaryStage;
		// Creating Images for the icon and Background and setting the Icon and Game Title
		loadingScreen = new StackPane();
		window.setTitle("Marvel Ultimate War!");
		Start = new Button("Start the Game!");
		Start.setOnAction(this);
		loadingScreen.getChildren().add(Start);
		loadingScreenScene = new Scene(loadingScreen, 1400,1000);
		window.setScene(loadingScreenScene);
		window.show();
	}
	public void EnteringNames() {
		Names = new StackPane();
		Player1NameLabel = new Label("Player 1 Please Enter Your Name!");
		Player2NameLabel = new Label("Player 2 Please Enter Your Name!");
		Player1NameInput = new TextField();
		Player2NameInput = new TextField();
		Player1NameInputLabel = new HBox(Player1NameLabel, Player1NameInput);
		Player2NameInputLabel = new HBox(Player2NameLabel, Player2NameInput);
		Player12NameInputLabel = new VBox(Player1NameInputLabel, Player2NameInputLabel);
		Player12NameInputLabel.setAlignment(Pos.CENTER);
		ChooseYourChampions = new Button("Choose Your Champions!");
		ChooseYourChampions.setOnAction(this);
		Names.getChildren().addAll(Player12NameInputLabel, ChooseYourChampions);
		NamesInputScene = new Scene(Names,1400,1000);
		window.setScene(NamesInputScene);
	}
	public void ChoosingChampionsPhase() {
		ChoosingChampionsStackPane = new StackPane();
		HBox allChamps = new HBox(8);
		HBox allChamps2 = new HBox(7);
		for (int i = 0; i < Game.getAvailableChampions().size(); i++) {
			String sType;
			String Ability1Name = Game.getAvailableChampions().get(i).getAbilities().get(0).getName();
			String Ability2Name = Game.getAvailableChampions().get(i).getAbilities().get(1).getName();
			String Ability3Name = Game.getAvailableChampions().get(i).getAbilities().get(2).getName();
			if (Game.getAvailableChampions().get(i) instanceof Hero) {
				sType = "Hero";
			}
			else if (Game.getAvailableChampions().get(i) instanceof AntiHero) {
				sType = "Anti Hero";
			} else {
				sType = "Villain";
			}
			HBox CType = new HBox(new Text("Type: " + sType));
			HBox CName = new HBox(new Text("Name: " + Game.getAvailableChampions().get(i).getName()));
			HBox CMaxHP = new HBox(new Text("MaxHP: " + Game.getAvailableChampions().get(i).getMaxHP()));
			HBox CMana = new HBox(new Text("Mana: " + Game.getAvailableChampions().get(i).getMana()));
			HBox CActions = new HBox(
					new Text("Action Points: " + Game.getAvailableChampions().get(i).getMaxActionPointsPerTurn()));
			HBox CSpeed = new HBox(new Text("Speed: " + Game.getAvailableChampions().get(i).getSpeed()));
			HBox CRange = new HBox(new Text("Range: " + Game.getAvailableChampions().get(i).getAttackRange()));
			HBox CDamage = new HBox(new Text("Damage: " + Game.getAvailableChampions().get(i).getAttackDamage()));
			HBox CAbility1 = new HBox(new Text("Ability 1: " + Ability1Name));
			HBox CAbility2 = new HBox(new Text("Ability 2: " + Ability2Name));
			HBox CAbility3 = new HBox(new Text("Ability 3: " + Ability3Name));
			VBox Champ = new VBox(CName, CType, CMaxHP, CMana, CActions, CSpeed, CRange, CDamage, CAbility1, CAbility2,
					CAbility3);
			if (i < 8) {
				allChamps.getChildren().add(Champ);
			} else {
				allChamps2.getChildren().add(Champ);
			}
		}
		allChamps.setSpacing(2);
		allChamps.setAlignment(Pos.CENTER);
		allChamps2.setSpacing(2);
		allChamps2.setAlignment(Pos.CENTER);
		VBox allChampsTogether = new VBox(allChamps, allChamps2);
		allChampsTogether.setSpacing(5);
		allChampsTogether.setAlignment(Pos.TOP_CENTER);
		CaptainAmerica1 = new CheckBox("Captain America");
		Deadpool1 = new CheckBox("Deadpool");
		DrStrange1 = new CheckBox("Dr Strange");
		Electro1 = new CheckBox("Electro");
		GhostRider1 = new CheckBox("Ghost Rider");
		Hela1 = new CheckBox("Hela");
		Hulk1 = new CheckBox("Hulk");
		Iceman1 = new CheckBox("Iceman");
		Ironman1 = new CheckBox("Ironman");
		Loki1 = new CheckBox("Loki");
		Quicksilver1 = new CheckBox("Quicksilver");
		Spiderman1 = new CheckBox("Spiderman");
		Thor1 = new CheckBox("Thor");
		Venom1 = new CheckBox("Venom");
		YellowJacket1 = new CheckBox("Yellow Jacket");
		player1CheckBoxes = new ArrayList<CheckBox>();
		player1CheckBoxes.add(CaptainAmerica1);
		player1CheckBoxes.add(Deadpool1);
		player1CheckBoxes.add(DrStrange1);
		player1CheckBoxes.add(Electro1);
		player1CheckBoxes.add(GhostRider1);
		player1CheckBoxes.add(Hela1);
		player1CheckBoxes.add(Hulk1);
		player1CheckBoxes.add(Iceman1);
		player1CheckBoxes.add(Ironman1);
		player1CheckBoxes.add(Loki1);
		player1CheckBoxes.add(Quicksilver1);
		player1CheckBoxes.add(Spiderman1);
		player1CheckBoxes.add(Thor1);
		player1CheckBoxes.add(Venom1);
		player1CheckBoxes.add(YellowJacket1);
		Label Player1Select = new Label(Player1NameInput.getText() +  " Please select your Champions");
		VBox Player1CheckList = new VBox(Player1Select, CaptainAmerica1, Deadpool1, DrStrange1, Electro1, GhostRider1,
				Hela1, Hulk1, Iceman1, Ironman1, Loki1, Quicksilver1, Spiderman1, Thor1, Venom1, YellowJacket1);
		Player1CheckList.setAlignment(Pos.CENTER_LEFT);
		CaptainAmerica2 = new CheckBox("Captain America");
		Deadpool2 = new CheckBox("Deadpool");
		DrStrange2 = new CheckBox("Dr Strange");
		Electro2 = new CheckBox("Electro");
		GhostRider2 = new CheckBox("Ghost Rider");
		Hela2 = new CheckBox("Hela");
		Hulk2 = new CheckBox("Hulk");
		Iceman2 = new CheckBox("Iceman");
		Ironman2 = new CheckBox("Ironman");
		Loki2 = new CheckBox("Loki");
		Quicksilver2 = new CheckBox("Quicksilver");
		Spiderman2 = new CheckBox("Spiderman");
		Thor2 = new CheckBox("Thor");
		Venom2 = new CheckBox("Venom");
		YellowJacket2 = new CheckBox("Yellow Jacket");
		player2CheckBoxes = new ArrayList<CheckBox>();
		player2CheckBoxes.add(CaptainAmerica2);
		player2CheckBoxes.add(Deadpool2);
		player2CheckBoxes.add(DrStrange2);
		player2CheckBoxes.add(Electro2);
		player2CheckBoxes.add(GhostRider2);
		player2CheckBoxes.add(Hela2);
		player2CheckBoxes.add(Hulk2);
		player2CheckBoxes.add(Iceman2);
		player2CheckBoxes.add(Ironman2);
		player2CheckBoxes.add(Loki2);
		player2CheckBoxes.add(Quicksilver2);
		player2CheckBoxes.add(Spiderman2);
		player2CheckBoxes.add(Thor2);
		player2CheckBoxes.add(Venom2);
		player2CheckBoxes.add(YellowJacket2);
		Label Player2Select = new Label(Player2NameInput.getText() +  " Please select your Champions");
		VBox Player2CheckList = new VBox(Player2Select, CaptainAmerica2, Deadpool2, DrStrange2, Electro2, GhostRider2,
				Hela2, Hulk2, Iceman2, Ironman2, Loki2, Quicksilver2, Spiderman2, Thor2, Venom2, YellowJacket2);
		Player2CheckList.setAlignment(Pos.CENTER_RIGHT);
		HBox checkLists1 = new HBox(Player1CheckList);
		checkLists1.setAlignment(Pos.CENTER_LEFT);
		HBox checkLists2 = new HBox(Player2CheckList);
		checkLists2.setAlignment(Pos.CENTER_RIGHT);
		HBox both = new HBox(checkLists1, checkLists2);
		both.setSpacing(200);
		ChooseLeaders = new Button("Choose Your Leaders");
		ChooseLeaders.setOnAction(this);
		ChooseLeaders.setAlignment(Pos.BOTTOM_CENTER);
		ChoosingChampionsStackPane.getChildren().addAll(allChampsTogether, both, ChooseLeaders);
		ChoosingChampionsScene = new Scene(ChoosingChampionsStackPane, 1400, 1000);
		window.setScene(ChoosingChampionsScene);
	}
	public void PickingLeadersPhase() {
		StartFight = new Button("StartFight");
		StartFight.setOnAction(this);
		Team1ChampionsCheckBoxes = new ArrayList<CheckBox>();
		Team2ChampionsCheckBoxes = new ArrayList<CheckBox>();
		LeadersStackPane = new StackPane();
		CheckBox Player1Champion1 = new CheckBox(Player1.getTeam().get(0).getName());
		CheckBox Player1Champion2 = new CheckBox(Player1.getTeam().get(1).getName());
		CheckBox Player1Champion3 = new CheckBox(Player1.getTeam().get(2).getName());
		CheckBox Player2Champion1 = new CheckBox(Player2.getTeam().get(0).getName());
		CheckBox Player2Champion2 = new CheckBox(Player2.getTeam().get(1).getName());
		CheckBox Player2Champion3 = new CheckBox(Player2.getTeam().get(2).getName());
		Team1ChampionsCheckBoxes.add(Player1Champion1);
		Team1ChampionsCheckBoxes.add(Player1Champion2);
		Team1ChampionsCheckBoxes.add(Player1Champion3);
		Team2ChampionsCheckBoxes.add(Player2Champion1);
		Team2ChampionsCheckBoxes.add(Player2Champion2);
		Team2ChampionsCheckBoxes.add(Player2Champion3);
		Label Tag1 = new Label(Player1NameInput.getText() + " Please Choose Your Leader");
		Label Tag2 = new Label(Player2NameInput.getText() + " Please Choose Your Leader");
		VBox Team1Champs = new VBox(Tag1, Player1Champion1, Player1Champion2, Player1Champion3);
		VBox Team2Champs = new VBox(Tag2, Player2Champion1, Player2Champion2, Player2Champion3);
		HBox TeamChamps = new HBox(Team1Champs, Team2Champs, StartFight);
		LeadersStackPane.getChildren().add(TeamChamps);
		LeadersScene = new Scene(LeadersStackPane, 1400, 1000);
		window.setScene(LeadersScene);
	}
	public void addPlayerDetails() {
		name1Label = new Label(Player1.getName());
		team1Champion1Label = new Label(Player1.getTeam().get(0).getName());
		team1Champion2Label = new Label(Player1.getTeam().get(1).getName());
		team1Champion3Label = new Label(Player1.getTeam().get(2).getName());
		name2Label = new Label(Player2.getName());
		team2Champion1Label = new Label(Player2.getTeam().get(0).getName());
		team2Champion2Label = new Label(Player2.getTeam().get(1).getName());
		team2Champion3Label = new Label(Player2.getTeam().get(2).getName());
	}
	public void PlacingChampionsAndCovers() {
		Board = new GridPane();
		target = new ChoiceBox<String>();
		target.getItems().add("Please Choose 1 Champion");
		target.setValue("Please Choose 1 Champion");
		for(int i = 0; i < Player1.getTeam().size(); i++) {
			target.getItems().add(Player1.getTeam().get(i).getName());
		}
		for(int i = 0; i < Player2.getTeam().size(); i++) {
			target.getItems().add(Player2.getTeam().get(i).getName());
		}
		leaderAbilityUsedTeam1Label = new Label(Player1.getName() + " Leader Ability Used: " + game.isFirstLeaderAbilityUsed());
		MoveUp = new Button("Move Up");
		MoveUp.setOnAction(this);
		MoveDown = new Button("Move Down");
		MoveDown.setOnAction(this);
		MoveLeft = new Button("Move Left");
		MoveLeft.setOnAction(this);
		MoveRight = new Button("Move Right");
		MoveRight.setOnAction(this);
		EndTurn = new Button("End Turn");
		EndTurn.setOnAction(this);
		leaderAbilityUsedTeam2Label = new Label(Player2.getName() + " Leader Ability Used: " + game.isSecondLeaderAbilityUsed());
		Ability1 = new Button(game.getCurrentChampion().getAbilities().get(0).getName());
		Ability1.setOnAction(this);
		Ability2 = new Button(game.getCurrentChampion().getAbilities().get(1).getName());
		Ability2.setOnAction(this);
		Ability3 = new Button(game.getCurrentChampion().getAbilities().get(2).getName());
		Ability3.setOnAction(this);
		LeaderAbility1 = new Button("Use Leader Ability " + Player1.getName());
		LeaderAbility1.setOnAction(this);
		LeaderAbility2 = new Button("Use Leader Ability " + Player2.getName());
		LeaderAbility2.setOnAction(this);
		AttackDown = new Button("Attack Down");
		AttackDown.setOnAction(this);
		AttackUp = new Button("Attack Up");
		AttackUp.setOnAction(this);
		AttackLeft = new Button("Attack Left");
		AttackLeft.setOnAction(this);
		AttackRight = new Button("Attack Right");
		AttackRight.setOnAction(this);
		VBox temp1 = new VBox(name1Label , team1Champion1Label, team1Champion2Label, team1Champion3Label);
		Board.add(temp1, 0, 0);
		VBox temp2 = new VBox(name2Label, team2Champion1Label, team2Champion2Label, team2Champion3Label);
		Board.add(temp2, 6, 0);
		leaderAbilityUsedTeam1Label = new Label("Leader Ability Used: " + game.isFirstLeaderAbilityUsed());
		leaderAbilityUsedTeam2Label = new Label("Leader Ability Used: " + game.isSecondLeaderAbilityUsed());
		Board.add(leaderAbilityUsedTeam1Label, 0, 1);
		Board.add(leaderAbilityUsedTeam2Label, 6, 1);
		VBox temp3 = new VBox(MoveUp, MoveDown, MoveLeft, MoveRight);
		Board.add(temp3, 0, 2);
		VBox temp4 = new VBox(AttackUp, AttackDown, AttackLeft, AttackRight);
		Board.add(temp4, 0, 3);
		VBox temp5 = new VBox(LeaderAbility1,LeaderAbility2, EndTurn);
		Board.add(temp5, 0, 4);
		Object [][] RealBoard = game.getBoard();
		String t = DisplayPQ(game.getTurnOrder());
		Label pQLabel = new Label(t);
		Board.add(pQLabel, 1, 5);
		Ability1 = new Button(getithAbilityDetails(game.getCurrentChampion(), 0));
		Ability1.setOnAction(this);
		Ability2 = new Button(getithAbilityDetails(game.getCurrentChampion(), 1));
		Ability2.setOnAction(this);
		Ability3 = new Button(getithAbilityDetails(game.getCurrentChampion(), 2));
		Ability3.setOnAction(this);
		AbilityUp = new CheckBox("Up");
		AbilityDown = new CheckBox("Down");
		AbilityLeft = new CheckBox("Left");
		AbilityRight = new CheckBox("Right");
		AbilityDirections = new ArrayList<CheckBox>();
		AbilityDirections.add(AbilityLeft);
		AbilityDirections.add(AbilityRight);
		AbilityDirections.add(AbilityUp);
		AbilityDirections.add(AbilityDown);
		VBox AbilityDir = new VBox(AbilityUp, AbilityDown, AbilityLeft, AbilityRight);
		HBox inputs = new HBox(target, AbilityDir);
		Board.add(inputs, 7, 5);
		Label info = new Label("If Ability is Directional please pick a direction" + "\n" + "if Ability is Single Target please choose a target");
		Board.add(info, 7, 4);
		Board.add(Ability1, 6, 3);
		Board.add(Ability2, 6, 4);
		Board.add(Ability3, 6, 5);
		Button CurrentChamp = new Button("Current Champion: " + game.getCurrentChampion().getName());
		Board.add(CurrentChamp, 6, 2);
		if(RealBoard[0][0] instanceof Cover) {
			board00 = new Button("Cover" + ((Cover)RealBoard[0][0]).getCurrentHP());
			board00.setOnAction(this);
		}else if(RealBoard[0][0] instanceof Champion) {
			board00 = new Button(ChampionsDetails((Champion) RealBoard[0][0]));
			board00.setOnAction(this);
		}else {
			board00 = new Button("Empty Cell");
			board00.setOnAction(this);
		}
		if(RealBoard[1][0] instanceof Cover) {
			board10 = new Button("Cover" + "\n" + "Current HP: " + ((Cover)RealBoard[1][0]).getCurrentHP());
			board10.setOnAction(this);
		}else if(RealBoard[0][0] instanceof Champion) {
			board10 = new Button(ChampionsDetails((Champion) RealBoard[1][0]));
			board10.setOnAction(this);
		}else {
			board10 = new Button("Empty Cell");
			board10.setOnAction(this);
		}
		if(RealBoard[2][0] instanceof Cover) {
			board20 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[2][0]).getCurrentHP());
			board20.setOnAction(this);
		}else if(RealBoard[2][0] instanceof Champion) {
			board20 = new Button(ChampionsDetails((Champion) RealBoard[2][0]));
			board20.setOnAction(this);
		}else {
			board20 = new Button("Empty Cell");
			board20.setOnAction(this);
		}
		if(RealBoard[3][0] instanceof Cover) {
			board30 = new Button("Cover" + "\n" + "Current HP: " + ((Cover)RealBoard[3][0]).getCurrentHP());
			board30.setOnAction(this);
		}else if(RealBoard[3][0] instanceof Champion) {
			board30 = new Button(ChampionsDetails((Champion) RealBoard[3][0]));
			board30.setOnAction(this);
		}else {
			board30 = new Button("Empty Cell");
			board30.setOnAction(this);
		}
		if(RealBoard[4][0] instanceof Cover) {
			board40 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[4][0]).getCurrentHP());
			board40.setOnAction(this);
		}else if(RealBoard[4][0] instanceof Champion) {
			board40 = new Button(ChampionsDetails((Champion) RealBoard[4][0]));
			board40.setOnAction(this);
		}else {
			board40 = new Button("Empty Cell");
			board40.setOnAction(this);
		}
		if(RealBoard[0][1] instanceof Cover) {
			board01 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[0][1]).getCurrentHP());
			board01.setOnAction(this);
		}else if(RealBoard[0][1] instanceof Champion) {
			board01 = new Button(ChampionsDetails((Champion) RealBoard[0][1]));
			board01.setOnAction(this);
		}else {
			board01 = new Button("Empty Cell");
			board01.setOnAction(this);
		}
		if(RealBoard[1][1] instanceof Cover) {
			board11 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[1][1]).getCurrentHP());
			board11.setOnAction(this);
		}else if(RealBoard[1][1] instanceof Champion) {
			board11 = new Button(ChampionsDetails((Champion) RealBoard[1][1]));
			board11.setOnAction(this);
		}else {
			board11 = new Button("Empty Cell");
			board11.setOnAction(this);
		}
		if(RealBoard[2][1] instanceof Cover) {
			board21 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[2][1]).getCurrentHP());
			board21.setOnAction(this);
		}else if(RealBoard[2][1] instanceof Champion) {
			board21 = new Button(ChampionsDetails((Champion) RealBoard[2][1]));
			board21.setOnAction(this);
		}else {
			board21 = new Button("Empty Cell");
			board21.setOnAction(this);
		}
		if(RealBoard[3][1] instanceof Cover) {
			board31 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[3][1]).getCurrentHP());
			board31.setOnAction(this);
		}else if(RealBoard[3][1] instanceof Champion) {
			board31 = new Button(ChampionsDetails((Champion) RealBoard[3][1]));
			board31.setOnAction(this);
		}else {
			board31 = new Button("Empty Cell");
			board31.setOnAction(this);
		}
		if(RealBoard[4][1] instanceof Cover) {
			board41 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[4][1]).getCurrentHP());
			board41.setOnAction(this);
		}else if(RealBoard[4][1] instanceof Champion) {
			board41 = new Button(ChampionsDetails((Champion) RealBoard[4][1]));
			board41.setOnAction(this);
		}else {
			board41 = new Button("Empty Cell");
			board41.setOnAction(this);
		}
		if(RealBoard[0][2] instanceof Cover) {
			board02 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[0][2]).getCurrentHP());
			board02.setOnAction(this);
		}else if(RealBoard[0][2] instanceof Champion) {
			board02 = new Button(ChampionsDetails((Champion) RealBoard[0][2]));
			board02.setOnAction(this);
		}else {
			board02 = new Button("Empty Cell");
			board02.setOnAction(this);
		}
		if(RealBoard[1][2] instanceof Cover) {
			board12 = new Button("Cover" + "\n" + "Current HP: " + ((Cover)RealBoard[1][2]).getCurrentHP());
			board12.setOnAction(this);
		}else if(RealBoard[1][2] instanceof Champion) {
			board12 = new Button(ChampionsDetails((Champion) RealBoard[1][2]));
			board12.setOnAction(this);
		}else {
			board12 = new Button("Empty Cell");
			board12.setOnAction(this);
		}
		if(RealBoard[2][2] instanceof Cover) {
			board22 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[2][2]).getCurrentHP());
			board22.setOnAction(this);
		}else if(RealBoard[2][2] instanceof Champion) {
			board22 = new Button(ChampionsDetails((Champion) RealBoard[2][2]));
			board22.setOnAction(this);
		}else {
			board22 = new Button("Empty Cell");
			board22.setOnAction(this);
		}
		if(RealBoard[3][2] instanceof Cover) {
			board32 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[3][2]).getCurrentHP());
			board32.setOnAction(this);
		}else if(RealBoard[3][2] instanceof Champion) {
			board32 = new Button(ChampionsDetails((Champion) RealBoard[3][2]));
			board32.setOnAction(this);
		}else {
			board32 = new Button("Empty Cell");
			board32.setOnAction(this);
		}
		if(RealBoard[4][2] instanceof Cover) {
			board42 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[4][2]).getCurrentHP());
			board42.setOnAction(this);
		}else if(RealBoard[4][2] instanceof Champion) {
			board42 = new Button(ChampionsDetails((Champion) RealBoard[4][2]));
			board42.setOnAction(this);
		}else {
			board42 = new Button("Empty Cell");
			board42.setOnAction(this);
		}
		if(RealBoard[0][3] instanceof Cover) {
			board03 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[0][3]).getCurrentHP());
			board03.setOnAction(this);
		}else if(RealBoard[0][3] instanceof Champion) {
			board03 = new Button(ChampionsDetails((Champion) RealBoard[0][3]));
			board03.setOnAction(this);
		}else {
			board03 = new Button("Empty Cell");
			board03.setOnAction(this);
		}
		if(RealBoard[1][3] instanceof Cover) {
			board13 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[1][3]).getCurrentHP());
			board13.setOnAction(this);
		}else if(RealBoard[1][3] instanceof Champion) {
			board13 = new Button(ChampionsDetails((Champion) RealBoard[1][3]));
			board13.setOnAction(this);
		}else {
			board13 = new Button("Empty Cell");
			board13.setOnAction(this);
		}
		if(RealBoard[2][3] instanceof Cover) {
			board23 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[2][3]).getCurrentHP());
			board23.setOnAction(this);
		}else if(RealBoard[2][3] instanceof Champion) {
			board23 = new Button(ChampionsDetails((Champion) RealBoard[2][3]));
			board23.setOnAction(this);
		}else {
			board23 = new Button("Empty Cell");
			board23.setOnAction(this);
		}
		if(RealBoard[3][3] instanceof Cover) {
			board33 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[3][3]).getCurrentHP());
			board33.setOnAction(this);
		}else if(RealBoard[3][3] instanceof Champion) {
			board33 = new Button(ChampionsDetails((Champion) RealBoard[3][3]));
			board33.setOnAction(this);
		}else {
			board33 = new Button("Empty Cell");
			board33.setOnAction(this);
		}
		if(RealBoard[4][3] instanceof Cover) {
			board43 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[4][3]).getCurrentHP());
			board43.setOnAction(this);
		}else if(RealBoard[4][3] instanceof Champion) {
			board43 = new Button(ChampionsDetails((Champion) RealBoard[4][3]));
			board43.setOnAction(this);
		}else {
			board43 = new Button("Empty Cell");
			board43.setOnAction(this);
		}
		if(RealBoard[0][4] instanceof Cover) {
			board04 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[0][4]).getCurrentHP());
			board04.setOnAction(this);
		}else if(RealBoard[0][4] instanceof Champion) {
			board04 = new Button(ChampionsDetails((Champion) RealBoard[0][4]));
			board04.setOnAction(this);
		}else {
			board04 = new Button("Empty Cell");
			board04.setOnAction(this);
		}
		if(RealBoard[1][4] instanceof Cover) {
			board14 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[1][4]).getCurrentHP());
			board14.setOnAction(this);
		}else if(RealBoard[1][4] instanceof Champion) {
			board14 = new Button(ChampionsDetails((Champion) RealBoard[1][4]));
			board14.setOnAction(this);
		}else {
			board14 = new Button("Empty Cell");
			board14.setOnAction(this);
		}
		if(RealBoard[2][4] instanceof Cover) {
			board24 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[2][4]).getCurrentHP());
			board24.setOnAction(this);
		}else if(RealBoard[2][4] instanceof Champion) {
			board24 = new Button(ChampionsDetails((Champion) RealBoard[2][4]));
			board24.setOnAction(this);
		}else {
			board24 = new Button("Empty Cell");
			board24.setOnAction(this);
		}
		if(RealBoard[3][4] instanceof Cover) {
			board34 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[3][4]).getCurrentHP());
			board34.setOnAction(this);
		}else if(RealBoard[3][4] instanceof Champion) {
			board34 = new Button(ChampionsDetails((Champion) RealBoard[3][4]));
			board34.setOnAction(this);
		}else {
			board34 = new Button("Empty Cell");
			board34.setOnAction(this);
		}
		if(RealBoard[4][4] instanceof Cover) {
			board44 = new Button("Cover" +  "\n" + "Current HP: " +((Cover)RealBoard[4][4]).getCurrentHP());
			board44.setOnAction(this);
		}else if(RealBoard[4][4] instanceof Champion) {
			board44 = new Button(ChampionsDetails((Champion) RealBoard[4][4]));
			board44.setOnAction(this);
		}else {
			board44 = new Button("Empty Cell");
			board44.setOnAction(this);
		}
		Board.add(board00, 1, 4);
		Board.add(board10, 1, 3);
		Board.add(board20, 1, 2);
		Board.add(board30, 1, 1);
		Board.add(board40, 1, 0);
		Board.add(board01, 2, 4);
		Board.add(board11, 2, 3);
		Board.add(board21, 2, 2);
		Board.add(board31, 2, 1);
		Board.add(board41, 2, 0);
		Board.add(board02, 3, 4);
		Board.add(board12, 3, 3);
		Board.add(board22, 3, 2);
		Board.add(board32, 3, 1);
		Board.add(board42, 3, 0);
		Board.add(board03, 4, 4);
		Board.add(board13, 4, 3);
		Board.add(board23, 4, 2);
		Board.add(board33, 4, 1);
		Board.add(board43, 4, 0);
		Board.add(board04, 5, 4);
		Board.add(board14, 5, 3);
		Board.add(board24, 5, 2);
		Board.add(board34, 5, 1);
		Board.add(board44, 5, 0);
		Board.setHgap(2);
		Board.setVgap(2);
		
		Playing = new Scene(Board,1400,1000);
		window.setScene(Playing);
	}
	public void Winners() {
		Player temp = findWinner(game);
		Label win = new Label("Winner is: " + temp.getName());
		Scene wins = new Scene(win,980,540);
		window.setScene(wins);
	}
	public Player findWinner(Game game) {
		return game.checkGameOver();
	}
	public String ChampionsDetails(Champion c) {
		if(c!= null) {
		String t ="Name: ";
		t = t + c.getName() + "\n" + "Type: ";
		if(c instanceof Hero) {
			t = t + "Hero" + "\n";
		}else if(c instanceof AntiHero) {
			t = t + "AntiHero" + "\n";
		}else {
			t = t + "Villain" + "\n";
		}
		t = t + "Current HP: " + c.getCurrentHP() + "\n" + "Mana: " + c.getMana() + "\n" + "Current Action Points: " + c.getCurrentActionPoints() + "\n" + "Attack Damage: " + c.getAttackDamage() + "\n" + "Attack Range: " + c.getAttackRange() + "\n" + "Leader: ";
		boolean lead = false;
		if(c == Player1.getLeader() || c == Player2.getLeader()) {
			lead = true;
		}
		t = t + lead + "\n" + "Effects:" + "\n";
		for(int i = 0; i < c.getAppliedEffects().size(); i++) {
			t = t  + ". Effect Name: " + c.getAppliedEffects().get(i).getName() + "\n" + "Effect Duration: " + c.getAppliedEffects().get(i).getDuration() + "\n";
		}
		return t;
		}else {
			return null;
		}
	}
	public String getithAbilityDetails(Champion c, int n) {
		String type;
		String special;
		if(c.getAbilities().get(n) instanceof HealingAbility) {
			type = "Healing Ability";
			special = "Healing Amount: " + ((HealingAbility)c.getAbilities().get(n)).getHealAmount();
		}
		else if(c.getAbilities().get(n) instanceof DamagingAbility) {
			type = "Damaging Ability";
			special = "Damaging Amount: " + ((DamagingAbility)c.getAbilities().get(n)).getDamageAmount();
		}
		else {
			type = "Crowd Control Ability";
			special = "Effect Name: " + ((CrowdControlAbility)c.getAbilities().get(n)).getEffect().getName() + "\n" + "Effect Duration: " + ((CrowdControlAbility)c.getAbilities().get(n)).getEffect().getDuration();
		}
		String t = "Ability Name: " + c.getAbilities().get(n).getName() + "\n" + "Type: " + type + "\n" + "Area of Effect: " + c.getAbilities().get(n).getCastArea()
				+ "\n" + "Cast Range: " + c.getAbilities().get(n).getCastRange() + "\n" + "Required Mana: " + c.getAbilities().get(n).getManaCost()
				+ "\n" + "Required Action Points: " + c.getAbilities().get(n).getRequiredActionPoints() + "\n" + "Current Cooldown: " + c.getAbilities().get(n).getCurrentCooldown()
				+ "\n" + "Base CoolDown: " + c.getAbilities().get(n).getBaseCooldown() + "\n" + special;
		return t;
	}
	public String DisplayPQ(PriorityQueue T) {
		PriorityQueue temp = new PriorityQueue(6);
		String s = "Turn Order: " + "\n";
		while(!T.isEmpty()) {
			s = s + ((Champion) T.peekMin()).getName() + "\n";
			temp.insert(T.remove());
		}
		while(!temp.isEmpty()) {
			T.insert(temp.remove());
		}
		return s;
	}
	public Champion findChampion(String s) {
		Champion c;
		for(int i = 0; i < Player1.getTeam().size(); i ++) {
			if(Player1.getTeam().get(i).getName() == s) {
				c = Player1.getTeam().get(i);
				return c;
			}
		}
		for(int i = 0; i < Player2.getTeam().size(); i ++) {
			if(Player2.getTeam().get(i).getName() == s) {
				c = Player2.getTeam().get(i);
				return c;
			}
		}
		return null;
	}
	public boolean checkGameOver(Game game) {
		Player temp = game.checkGameOver();
		if(temp == null)
			return false;
		return true;
	}
	public void handle(ActionEvent Event) {
		if(Event.getSource() == Ability3) {
			Ability a = game.getCurrentChampion().getAbilities().get(2);
			if(a.getCastArea() == AreaOfEffect.TEAMTARGET || a.getCastArea() == AreaOfEffect.SELFTARGET || a.getCastArea() == AreaOfEffect.SURROUND) {
				try {
					game.castAbility(a);
				} catch (NotEnoughResourcesException e) {
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText("Not Enough Resources");
					error.setContentText("Not Enough Resources");
					error.showAndWait();
				} catch (AbilityUseException e) {
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText("Ability Use Exception");
					error.setContentText("Ability USe Exception");
					error.showAndWait();
				} catch (CloneNotSupportedException e) {
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText("Clone not Supported");
					error.setContentText("Clone not Supported");
					error.showAndWait();
				}
			}
			else if(a.getCastArea() == AreaOfEffect.DIRECTIONAL) {
				if(Check1Selected(AbilityDirections)) {
					Direction d;
					if(AbilityDown.isSelected())
						d = Direction.DOWN;
					else if(AbilityLeft.isSelected())
						d = Direction.LEFT;
					else if(AbilityUp.isSelected())
						d = Direction.UP;
					else
						d = Direction.RIGHT;
					try {
						game.castAbility(a,d);
					} catch (NotEnoughResourcesException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Not Enough Resources");
						error.setContentText("Not Enough Resources");
						error.showAndWait();
					} catch (AbilityUseException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Ability Use Exception");
						error.setContentText("Ability USe Exception");
						error.showAndWait();
					} catch (CloneNotSupportedException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Clone not Supported");
						error.setContentText("Clone not Supported");
						error.showAndWait();
					}
				}else {
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText("Please Choose Exactly 1 Direction");
					error.setContentText("Please Choose Exactly 1 Direction");
					error.showAndWait();
				}
			}
			else if(a.getCastArea() == AreaOfEffect.SINGLETARGET) {
				if(target.getValue().equals("Please Choose 1 Champion")) {
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText("Please Choose a target");
					error.setContentText("Please Choose a target");
					error.showAndWait();
				}else {
					Champion c = findChampion(target.getValue());
					try {
						game.castAbility(a,c.getLocation().x, c.getLocation().y);
					} catch (NotEnoughResourcesException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Not Enough Resources");
						error.setContentText("Not Enough Resources");
						error.showAndWait();
					} catch (AbilityUseException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Ability Use Exception");
						error.setContentText("Ability USe Exception");
						error.showAndWait();
					} catch (InvalidTargetException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Invalid Target");
						error.setContentText("Invalid Target");
						error.showAndWait();
					} catch (CloneNotSupportedException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Clone not Supported");
						error.setContentText("Clone not Supported");
						error.showAndWait();
					}
				}
			}
			if(checkGameOver(game)) {
				Winners();
			}else {
				PlacingChampionsAndCovers();
			}
		}
		if(Event.getSource() == Ability2) {
			Ability a = game.getCurrentChampion().getAbilities().get(1);
			if(a.getCastArea() == AreaOfEffect.TEAMTARGET || a.getCastArea() == AreaOfEffect.SELFTARGET || a.getCastArea() == AreaOfEffect.SURROUND) {
				try {
					game.castAbility(a);
				} catch (NotEnoughResourcesException e) {
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText("Not Enough Resources");
					error.setContentText("Not Enough Resources");
					error.showAndWait();
				} catch (AbilityUseException e) {
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText("Ability Use Exception");
					error.setContentText("Ability USe Exception");
					error.showAndWait();
				} catch (CloneNotSupportedException e) {
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText("Clone not Supported");
					error.setContentText("Clone not Supported");
					error.showAndWait();
				}
			}
			else if(a.getCastArea() == AreaOfEffect.DIRECTIONAL) {
				if(Check1Selected(AbilityDirections)) {
					Direction d;
					if(AbilityDown.isSelected())
						d = Direction.DOWN;
					else if(AbilityLeft.isSelected())
						d = Direction.LEFT;
					else if(AbilityUp.isSelected())
						d = Direction.UP;
					else
						d = Direction.RIGHT;
					try {
						game.castAbility(a,d);
					} catch (NotEnoughResourcesException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Not Enough Resources");
						error.setContentText("Not Enough Resources");
						error.showAndWait();
					} catch (AbilityUseException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Ability Use Exception");
						error.setContentText("Ability USe Exception");
						error.showAndWait();
					} catch (CloneNotSupportedException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Clone not Supported");
						error.setContentText("Clone not Supported");
						error.showAndWait();
					}
				}else {
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText("Please Choose Exactly 1 Direction");
					error.setContentText("Please Choose Exactly 1 Direction");
					error.showAndWait();
				}
			}
			else if(a.getCastArea() == AreaOfEffect.SINGLETARGET) {
				if(target.getValue().equals("Please Choose 1 Champion")) {
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText("Please Choose a target");
					error.setContentText("Please Choose a target");
					error.showAndWait();
				}else {
					Champion c = findChampion(target.getValue());
					try {
						game.castAbility(a,c.getLocation().x, c.getLocation().y);
					} catch (NotEnoughResourcesException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Not Enough Resources");
						error.setContentText("Not Enough Resources");
						error.showAndWait();
					} catch (AbilityUseException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Ability Use Exception");
						error.setContentText("Ability USe Exception");
						error.showAndWait();
					} catch (InvalidTargetException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Invalid Target");
						error.setContentText("Invalid Target");
						error.showAndWait();
					} catch (CloneNotSupportedException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Clone not Supported");
						error.setContentText("Clone not Supported");
						error.showAndWait();
					}
				}
			}
			if(checkGameOver(game)) {
				Winners();
			}else {
				PlacingChampionsAndCovers();
			}
		}
		if(Event.getSource() == Ability1) {
			Ability a = game.getCurrentChampion().getAbilities().get(0);
			if(a.getCastArea() == AreaOfEffect.TEAMTARGET || a.getCastArea() == AreaOfEffect.SELFTARGET || a.getCastArea() == AreaOfEffect.SURROUND) {
				try {
					game.castAbility(a);
				} catch (NotEnoughResourcesException e) {
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText("Not Enough Resources");
					error.setContentText("Not Enough Resources");
					error.showAndWait();
				} catch (AbilityUseException e) {
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText("Ability Use Exception");
					error.setContentText("Ability USe Exception");
					error.showAndWait();
				} catch (CloneNotSupportedException e) {
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText("Clone not Supported");
					error.setContentText("Clone not Supported");
					error.showAndWait();
				}
			}
			else if(a.getCastArea() == AreaOfEffect.DIRECTIONAL) {
				if(Check1Selected(AbilityDirections)) {
					Direction d;
					if(AbilityDown.isSelected())
						d = Direction.DOWN;
					else if(AbilityLeft.isSelected())
						d = Direction.LEFT;
					else if(AbilityUp.isSelected())
						d = Direction.UP;
					else
						d = Direction.RIGHT;
					try {
						game.castAbility(a,d);
					} catch (NotEnoughResourcesException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Not Enough Resources");
						error.setContentText("Not Enough Resources");
						error.showAndWait();
					} catch (AbilityUseException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Ability Use Exception");
						error.setContentText("Ability USe Exception");
						error.showAndWait();
					} catch (CloneNotSupportedException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Clone not Supported");
						error.setContentText("Clone not Supported");
						error.showAndWait();
					}
				}else {
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText("Please Choose Exactly 1 Direction");
					error.setContentText("Please Choose Exactly 1 Direction");
					error.showAndWait();
				}
			}
			else if(a.getCastArea() == AreaOfEffect.SINGLETARGET) {
				if(target.getValue().equals("Please Choose 1 Champion")) {
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText("Please Choose a target");
					error.setContentText("Please Choose a target");
					error.showAndWait();
				}else {
					Champion c = findChampion(target.getValue());
					try {
						game.castAbility(a,c.getLocation().x, c.getLocation().y);
					} catch (NotEnoughResourcesException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Not Enough Resources");
						error.setContentText("Not Enough Resources");
						error.showAndWait();
					} catch (AbilityUseException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Ability Use Exception");
						error.setContentText("Ability USe Exception");
						error.showAndWait();
					} catch (InvalidTargetException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Invalid Target");
						error.setContentText("Invalid Target");
						error.showAndWait();
					} catch (CloneNotSupportedException e) {
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Clone not Supported");
						error.setContentText("Clone not Supported");
						error.showAndWait();
					}
				}
			}
			if(checkGameOver(game)) {
				Winners();
			}else {
				PlacingChampionsAndCovers();
			}
		}
		if(Event.getSource() == LeaderAbility1) {
			try {
				game.useLeaderAbility();
			} catch (LeaderNotCurrentException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Leader isn't current Champion");
				error.setContentText("Leader isn't current Champion");
				error.showAndWait();
			} catch (LeaderAbilityAlreadyUsedException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Leader Ability Already Used");
				error.setContentText("Leader Ability Already Used");
				error.showAndWait();
			}
			if(checkGameOver(game)) {
				Winners();
			}else {
				PlacingChampionsAndCovers();
			}
		}
		if(Event.getSource() == LeaderAbility2) {
			try {
				game.useLeaderAbility();
			} catch (LeaderNotCurrentException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Leader isn't current Champion");
				error.setContentText("Leader isn't current Champion");
				error.showAndWait();
			} catch (LeaderAbilityAlreadyUsedException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Leader Ability Already Used");
				error.setContentText("Leader Ability Already Used");
				error.showAndWait();
			}
			if(checkGameOver(game)) {
				Winners();
			}else {
				PlacingChampionsAndCovers();
			}
		}
		if(Event.getSource() == AttackUp) {
			try {
				game.attack(Direction.UP);
			} catch (NotEnoughResourcesException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Not Enough Resources");
				error.setContentText("Not Enough Resources");
				error.showAndWait();
			} catch (ChampionDisarmedException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Champion Is Disarmed");
				error.setContentText("Champion Is Disarmed");
				error.showAndWait();
			} catch (InvalidTargetException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Invalid Target");
				error.setContentText("Invalid Target");
				error.showAndWait();
			}
			if(checkGameOver(game)) {
				Winners();
			}else {
				PlacingChampionsAndCovers();
			}
		}
		if(Event.getSource() == AttackDown) {
			try {
				game.attack(Direction.DOWN);
			} catch (NotEnoughResourcesException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Not Enough Resources");
				error.setContentText("Not Enough Resources");
				error.showAndWait();
			} catch (ChampionDisarmedException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Champion Is Disarmed");
				error.setContentText("Champion Is Disarmed");
				error.showAndWait();
			} catch (InvalidTargetException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Invalid Target");
				error.setContentText("Invalid Target");
				error.showAndWait();
			}
			if(checkGameOver(game)) {
				Winners();
			}else {
				PlacingChampionsAndCovers();
			}
		}
		if(Event.getSource() == AttackLeft) {
			try {
				game.attack(Direction.LEFT);
			} catch (NotEnoughResourcesException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Not Enough Resources");
				error.setContentText("Not Enough Resources");
				error.showAndWait();
			} catch (ChampionDisarmedException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Champion Is Disarmed");
				error.setContentText("Champion Is Disarmed");
				error.showAndWait();
			} catch (InvalidTargetException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Invalid Target");
				error.setContentText("Invalid Target");
				error.showAndWait();
			}
			if(checkGameOver(game)) {
				Winners();
			}else {
				PlacingChampionsAndCovers();
			}
		}
		if(Event.getSource() == AttackRight) {
			try {
				game.attack(Direction.RIGHT);
			} catch (NotEnoughResourcesException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Not Enough Resources");
				error.setContentText("Not Enough Resources");
				error.showAndWait();
			} catch (ChampionDisarmedException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Champion Is Disarmed");
				error.setContentText("Champion Is Disarmed");
				error.showAndWait();
			} catch (InvalidTargetException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Invalid Target");
				error.setContentText("Invalid Target");
				error.showAndWait();
			}
			if(checkGameOver(game)) {
				Winners();
			}else {
				PlacingChampionsAndCovers();
			}
		}
		if(Event.getSource() == MoveDown) {
			try {
				game.move(Direction.DOWN);
			} catch (NotEnoughResourcesException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Not Enough Resources");
				error.setContentText("Not Enough Resources");
				error.showAndWait();
			} catch (UnallowedMovementException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Unallowed Movement");
				error.setContentText("Unallowed Movement");
				error.showAndWait();
			}
			if(checkGameOver(game)) {
				Winners();
			}else {
				PlacingChampionsAndCovers();
			}
		}
		if(Event.getSource() == MoveUp) {
			try {
				game.move(Direction.UP);
			} catch (NotEnoughResourcesException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Not Enough Resources");
				error.setContentText("Not Enough Resources");
				error.showAndWait();
			} catch (UnallowedMovementException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Unallowed Movement");
				error.setContentText("Unallowed Movement");
				error.showAndWait();
			}
			if(checkGameOver(game)) {
				Winners();
			}else {
				PlacingChampionsAndCovers();
			}
		}
		if(Event.getSource() == MoveLeft) {
			try {
				game.move(Direction.LEFT);
			} catch (NotEnoughResourcesException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Not Enough Resources");
				error.setContentText("Not Enough Resources");
				error.showAndWait();
			} catch (UnallowedMovementException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Unallowed Movement");
				error.setContentText("Unallowed Movement");
				error.showAndWait();
			}
			if(checkGameOver(game)) {
				Winners();
			}else {
				PlacingChampionsAndCovers();
			}
		}
		if(Event.getSource() == EndTurn) {
			game.endTurn();
			if(checkGameOver(game)) {
				Winners();
			}else {
				PlacingChampionsAndCovers();
			}
		}
		if(Event.getSource() == MoveRight) {
			try {
				game.move(Direction.RIGHT);
			} catch (NotEnoughResourcesException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Not Enough Resources");
				error.setContentText("Not Enough Resources");
				error.showAndWait();
			} catch (UnallowedMovementException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Unallowed Movement");
				error.setContentText("Unallowed Movement");
				error.showAndWait();
			}
			if(checkGameOver(game)) {
				Winners();
			}else {
				PlacingChampionsAndCovers();
			}
		}
		if(Event.getSource() == StartFight) {
			if(!Check1Selected(Team1ChampionsCheckBoxes)) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText(Player1NameInput.getText());
				error.setContentText("Please Choose exactly 1 Leader!");
				error.showAndWait();
			}
			if(!Check1Selected(Team2ChampionsCheckBoxes)){
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText(Player2NameInput.getText());
				error.setContentText("Please Choose exactly 1 Leader!");
				error.showAndWait();
			}
			else {
				for(int i = 0; i < Team1ChampionsCheckBoxes.size(); i++) {
					if(Team1ChampionsCheckBoxes.get(i).isSelected()) {
						Player1.setLeader(Player1.getTeam().get(i));
					}
					if(Team2ChampionsCheckBoxes.get(i).isSelected()) {
						Player2.setLeader(Player2.getTeam().get(i));
					}
				}
				game = new Game(Player1, Player2);
				game.placeChampions();
				addPlayerDetails();
				PlacingChampionsAndCovers();
			}
		}
		if(Event.getSource() == Start) {
			EnteringNames();
		}
		if(Event.getSource() == ChooseYourChampions) {
			if (Player1NameInput.getText().equals("") || Player2NameInput.getText().equals("")) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Unvalid Input!");
				error.setContentText("Please don't leave the names blank! Try again!");
				error.showAndWait();
			} else {
				Player1 = new Player(Player1NameInput.getText());
				Player2 = new Player(Player2NameInput.getText());
				game = new Game(Player1, Player2);
				try {
					Game.loadAbilities("Abilities.csv");
					Game.loadChampions("Champions.csv");
				} catch (IOException e) {
					e.printStackTrace();
				}
				ChoosingChampionsPhase();
			}
		}
		if(Event.getSource() == ChooseLeaders) {
			if (!CheckTeamsOf3(player1CheckBoxes)) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText(Player1NameInput.getText());
				error.setContentText("Please Choose exactly 3 Champions!");
				error.showAndWait();
			} else if (!CheckTeamsOf3(player2CheckBoxes)) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText(Player2NameInput.getText());
				error.setContentText("Please Choose exactly 3 Champions!");
				error.showAndWait();
			}
			else if (!CheckRepeatedChampions(player1CheckBoxes, player2CheckBoxes)) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Duplicate Champions!");
				error.setContentText("Please choose different Champions");
				error.showAndWait();
			} else {
				insertIntoTeams(Player1, player1CheckBoxes, Player2, player2CheckBoxes);
				PickingLeadersPhase();
			}
		}
	}
	public void insertIntoTeams(Player Player1, ArrayList<CheckBox> CheckBoxes1, Player Player2, ArrayList<CheckBox> CheckBoxes2) {
		for(int i = 0; i < CheckBoxes1.size(); i++) {
			if(CheckBoxes1.get(i).isSelected()) {
				Player1.getTeam().add(Game.getAvailableChampions().get(i));
			}
		}
		for(int i = 0; i < CheckBoxes2.size(); i++) {
			if(CheckBoxes2.get(i).isSelected()) {
				Player2.getTeam().add(Game.getAvailableChampions().get(i));
			}
		}
		
	}
	public boolean CheckTeamsOf3(ArrayList<CheckBox> team) {
		int size = 0;
		for (int i = 0; i < team.size(); i++) {
			if (team.get(i).isSelected() == true)
				size++;
		}
		if (size == 3)
			return true;
		else
			return false;
	}
	public boolean Check1Selected(ArrayList<CheckBox> Leaders) {
		int size = 0;
		for(int i = 0; i < Leaders.size(); i++) {
			if(Leaders.get(i).isSelected() == true)
				size++;
		}
		if(size == 1)
			return true;
		return false;
	}
	public boolean CheckRepeatedChampions(ArrayList<CheckBox> team1, ArrayList<CheckBox> team2) {
		for (int i = 0; i < team1.size(); i++) {
			if (team1.get(i).isSelected() == true && team2.get(i).isSelected() == true)
				return false;
		}
		return true;
	}
	public static void main(String[] args) {
		launch(args);
	}
}