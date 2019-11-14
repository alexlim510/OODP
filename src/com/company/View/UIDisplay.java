package com.company.View;

public class UIDisplay {
    private GeneralUI generalUI;

    public UIDisplay(GeneralUI ui){
        this.generalUI = ui;
    }

    public void displayHomePage(){
        this.generalUI.displayHomePage();
    }
}
