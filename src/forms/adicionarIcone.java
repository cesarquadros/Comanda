package forms;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

class adicionarIcone implements TableCellRenderer {
	private String pathIcone;
	JLabel label = new JLabel();
	ImageIcon icone;

	public adicionarIcone(String pathIcone) {
		this.pathIcone = pathIcone;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		icone = new ImageIcon(getClass().getResource("/imagens/excluir.png"));
		//label.setVerticalAlignment(column);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(icone);
		return label;
	}

}
