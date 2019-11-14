package com.company.View;

/**
 * UI holder to display different UI
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-13
 */
public class UIDisplay {
    private GeneralUI generalUI;

    public UIDisplay(GeneralUI ui){
        this.generalUI = ui;
    }

    public void displayHomePage(){
        this.generalUI.displayHomePage();
    }
}
