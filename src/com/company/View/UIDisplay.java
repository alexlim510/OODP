package com.company.View;

/**
 * UI holder to display different UI
 * @author Group 2 - SS6
 * @version 1.0
 * @since 2019-11-13
 */
public class UIDisplay {
    /**
     * Objects of classes that implements GeneralUI such as LoginUI, StaffUI and MoviegoerUI
     */
    private GeneralUI generalUI;

    /**
     * Creates UIDisplay given a GeneralUI object
     * @param ui GeneralUI object
     */
    public UIDisplay(GeneralUI ui){
        this.generalUI = ui;
    }

    /**
     * Display the UI's homepage
     */
    public void displayHomePage(){
        this.generalUI.displayHomePage();
    }
}
