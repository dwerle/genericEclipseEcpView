package edu.kit.ipd.are.genericecpeditor.views;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecp.ui.view.ECPRendererException;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTView;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTViewRenderer;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

// TODO: comment
public class GenericEcpView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "edu.kit.ipd.are.genericecpeditor.views.GenericEcpView";

	private Composite parent;
	private ECPSWTView renderedView;

	/**
	 * The constructor.
	 */
	public GenericEcpView() {
	}

	public void createPartControl(Composite parent) {
		this.parent = parent;
	}

	public void setFocus() {
		if ((renderedView != null) && (renderedView.getSWTControl() != null)) {
			renderedView.getSWTControl().setFocus();
		}
	}

	public void setTarget(EObject targetObject) {
		try {
			if (renderedView != null) {
				renderedView.getSWTControl().dispose();
				renderedView.dispose();
			}
			renderedView = ECPSWTViewRenderer.INSTANCE.render(parent, targetObject);
			parent.layout(true, true);
		} catch (ECPRendererException e) {
			MessageDialog.openError(
					parent.getShell(),
					"Genericecpeditor",
					e.toString());
		}
	}
}
