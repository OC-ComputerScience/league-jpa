package soccerLeagueUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import jakarta.persistence.EntityTransaction;
import soccerLeagueDAO.emDAO;
import soccerLeaguePD.League;
import soccerLeaguePD.Team;

public class TeamSelectionList extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TeamSelectionList(League league, JFrame currentFrame) {
		emDAO.getEM().refresh(league);
		setLayout(null);

		JLabel lblTeamSelectionList = new JLabel("Team Selection List");
		lblTeamSelectionList.setBounds(148, 26, 138, 16);
		add(lblTeamSelectionList);

		DefaultListModel<Team> listModel = new DefaultListModel<Team>();
		for (Team team : league.getTeams())
			listModel.addElement(team);

		JList<Team> list = new JList<Team>(listModel);
		list.setBounds(148, 66, 138, 102);
//		add(list);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(148, 66, 138, 102);
		scrollPane.setViewportView(list);
		add(scrollPane);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TeamEdit(new Team(), league, currentFrame, true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnAdd.setBounds(66, 193, 117, 29);
		add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TeamEdit(list.getSelectedValue(), league, currentFrame, false));
				currentFrame.getContentPane().revalidate();

			}
		});
		btnUpdate.setBounds(179, 193, 117, 29);
		add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Team team = list.getSelectedValue();
				if (team.isOkToRemove()) {
					EntityTransaction userTransaction = emDAO.getEM().getTransaction();
					userTransaction.begin();
					league.removeTeam(team);
					userTransaction.commit();
				}

				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TeamSelectionList(league, currentFrame));
				currentFrame.getContentPane().revalidate();

			}
		});
		btnDelete.setBounds(295, 193, 117, 29);
		add(btnDelete);

	}
}
