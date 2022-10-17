package note;

import activity.whenDo.CreateNoteForm;
import activity.whenDo.DeleteNoteForm;
import activity.whenDo.MainScreen;
import activity.whenDo.ModifyNoteForm;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import model.Nota;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import singletonSession.Session;

import static model.NotaBuilder.createNota;

public class NoteTest {
    MainScreen mainScreen = new MainScreen();
    CreateNoteForm createNoteForm = new CreateNoteForm();
    ModifyNoteForm modifyNoteForm=new ModifyNoteForm();
    DeleteNoteForm deleteNoteForm = new DeleteNoteForm();


    @Test
    @DisplayName("Verify Create Note")
    @Description("Create Note")
    public void verifyCreateNewNote(){

        String title="Cato";
        String note="esta es una nota";

        mainScreen.addNoteButton.click();
        createNoteForm.titleTxtBox.setText(title);
        createNoteForm.noteTxtBox.setText(note);
        createNoteForm.saveButton.click();

        Assertions.assertTrue(mainScreen.isNoteDisplayed(title),
                "ERROR, the note was not created");
    }

    @Test
    @DisplayName("Verify Modify Note")
    @Description("Modify Note")
    public void verifyModifyNote(){

        mainScreen.addNoteButton.click();

        Nota nota= createNota()
                .titulo("Peru")
                .notas("Lima")
                .build();

        createNoteForm.titleTxtBox.setText(nota.getTitulo());
        createNoteForm.noteTxtBox.setText(nota.getNotas());
        createNoteForm.saveButton.click();

        String title="PeruEdit";
        String note="Nota Lima Edit";

        modifyNoteForm.itemListLabel.click();
        createNoteForm.titleTxtBox.setText(title);
        createNoteForm.noteTxtBox.setText(note);
        createNoteForm.saveButton.click();

        Assertions.assertTrue(mainScreen.isNoteDisplayed(title),
                "ERROR, the note was not modify");
    }

    @Test
    @DisplayName("Verify Delete Note")
    @Description("Delete Note")
    public void verifyDeleteNote(){

        mainScreen.addNoteButton.click();

        Nota nota= createNota()
                .titulo("Peru delete")
                .notas("Lima delete")
                .build();

        createNoteForm.titleTxtBox.setText(nota.getTitulo());
        createNoteForm.noteTxtBox.setText(nota.getNotas());
        createNoteForm.saveButton.click();

        modifyNoteForm.itemListLabel.click();
        deleteNoteForm.deleteButton.click();
        deleteNoteForm.deleteConfirmationButton.click();

        Assertions.assertFalse(mainScreen.isNoteDisplayed(nota.getTitulo()),
                "ERROR, the note was not delete");
    }


    @AfterEach
    public void closeApp(){
        Session.getInstance().closeApp();
    }



}
