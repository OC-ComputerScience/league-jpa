package soccerLeagueUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jakarta.persistence.EntityTransaction;
import soccerLeagueDAO.emDAO;
import soccerLeaguePD.Game;
import soccerLeaguePD.League;
import soccerLeaguePD.Schedule;
import soccerLeaguePD.Team;

@SuppressWarnings({ "serial", "unused" })
public class GameEdit extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	JComboBox<Team> comboBox;
	JComboBox<Team> comboBox_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GameEdit(Game game, Schedule schedule, League league, JFrame currentFrame, boolean isScheduleAdd,
			boolean isAdd) {

		emDAO.getEM().refresh(league);
		if (!isAdd)
			emDAO.getEM().refresh(schedule);

		setLayout(null);

		JLabel lblTeamEdit = new JLabel("Game Edit");
		lblTeamEdit.setBounds(170, 31, 84, 16);
		add(lblTeamEdit);

		JLabel lblName = new JLabel("Date:");
		lblName.setBounds(47, 65, 61, 16);
		add(lblName);

		textField = new JTextField(game.getDateString());
		textField.setBounds(145, 59, 134, 28);
		add(textField);
		textField.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				EntityTransaction userTransaction = emDAO.getEM().getTransaction();
				userTransaction.begin();
				game.setDate(textField.getText());
				game.setHomeTeam((Team) comboBox.getSelectedItem());
				game.setVisitingTeam((Team) comboBox_1.getSelectedItem());
				game.setLocation(game.getHomeTeam().getHomeLocation());
				if (!textField_2.getText().isEmpty())
					game.setHomeTeamScore(Integer.parseInt(textField_2.getText()));
				if (!textField_3.getText().isEmpty())
					game.setVisitingTeamScore(Integer.parseInt(textField_3.getText()));
				if (!textField_4.getText().isEmpty())
					game.setHomeTeamPK(Integer.parseInt(textField_4.getText()));
				if (!textField_5.getText().isEmpty())
					game.setVisitingTeamPK(Integer.parseInt(textField_5.getText()));
				if (isAdd) {
					game.setSchedule(schedule);
					schedule.addGame(game);
				}
				userTransaction.commit();

				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ScheduleEdit(schedule, league, currentFrame, isScheduleAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(90, 242, 117, 29);
		add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ScheduleEdit(schedule, league, currentFrame, isScheduleAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(219, 242, 117, 29);
		add(btnCancel);

		comboBox = new JComboBox();
		for (Team team : league.getTeams())
			comboBox.addItem(team);
		if (!isAdd)
			comboBox.setSelectedItem(game.getHomeTeam());
		comboBox.setBounds(140, 99, 129, 27);
		add(comboBox);

		JLabel lblHomeTeam = new JLabel("Home Team :");
		lblHomeTeam.setBounds(35, 103, 102, 16);
		add(lblHomeTeam);

		comboBox_1 = new JComboBox();
		for (Team team : league.getTeams())
			comboBox_1.addItem(team);
		if (!isAdd)
			comboBox.setSelectedItem(game.getVisitingTeam());
		comboBox_1.setBounds(140, 141, 129, 27);
		add(comboBox_1);

		JLabel lblVisitingTeam = new JLabel("Visiting Team :");
		lblVisitingTeam.setBounds(35, 145, 102, 16);
		add(lblVisitingTeam);

		JLabel lblScore = new JLabel("Score");
		lblScore.setBounds(287, 80, 61, 16);
		add(lblScore);

		JLabel lblPk = new JLabel("PK");
		lblPk.setBounds(360, 80, 61, 16);
		add(lblPk);

		textField_2 = new JTextField(game.getHomeTeamScore());
		textField_2.setBounds(281, 97, 51, 28);
		add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField(game.getVisitingTeamScore());
		textField_3.setBounds(281, 140, 51, 26);
		add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField(game.getHomeTeamPK());
		textField_4.setBounds(344, 98, 51, 26);
		add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField(game.getVisitingTeamPK());
		textField_5.setBounds(344, 140, 51, 26);
		add(textField_5);
		textField_5.setColumns(10);
	}
}
