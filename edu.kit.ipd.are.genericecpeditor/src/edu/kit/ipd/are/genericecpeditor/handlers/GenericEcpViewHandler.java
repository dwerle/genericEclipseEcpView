package edu.kit.ipd.are.genericecpeditor.handlers;

import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.internal.registry.ViewRegistry;
import org.eclipse.ui.views.IViewDescriptor;

import edu.kit.ipd.are.genericecpeditor.views.GenericEcpView;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class GenericEcpViewHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
 		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if (!(currentSelection instanceof TreeSelection)) {
			MessageDialog.openError(
					window.getShell(),
					"Genericecpeditor",
					currentSelection.toString() + " is not a " + TreeSelection.class.getName());
			return null;
		}
		Object firstSelectedElement = ((TreeSelection) currentSelection).getFirstElement();
		if (!(firstSelectedElement instanceof EObject)) {
			MessageDialog.openError(
					window.getShell(),
					"Genericecpeditor",
					currentSelection.toString() + " is not an " + EObject.class.getName());
			return null;
		}
		EObject targetObject = (EObject) firstSelectedElement;
		
		try {
			GenericEcpView ecpView = (GenericEcpView) window.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(GenericEcpView.ID);
			ecpView.setTarget(targetObject);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
