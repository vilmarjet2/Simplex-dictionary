import java.util.Vector;

public class Vocabulary {
	
	private Integer Lg=1; /* 	1= Eng;
					2= Fr;
					3=Br;
				*/
	private  Vector<Vector<String> > vecVocTex;
	private  Vector<Vector<String> > vecVocMenu;

	public Vocabulary(int i)
	{
		Lg = new Integer(i);
		vecVocTex = new Vector<Vector<String> >();
		vecVocMenu = new Vector<Vector<String> >();
		
		LoadVocabularyMenu();
		LoadVocabularyText();
		
	}

	public void setLang(int i)
	{
		Lg = Integer.valueOf(i);
	}
	
	public String getText_index(int i)
	{		
		if (i < vecVocTex.size())
			return vecVocTex.get(i).get(Lg);
		else 
			return "Error in vocabulary (Index does not exist)";
	}
	
	
	public String getVoc_menu(int i)
	{		
		if (i < vecVocMenu.size())
			return vecVocMenu.get(i).get(Lg);
		else 
			return "Error in vocabulary (Index does not exist)";
	}
	
	
	
	public  void LoadVocabularyMenu()
	{
		Vector<String> v = new Vector<String>();
		v = new Vector<String>(); v.add("Menu"); v.add("Menu");v.add("Menu"); vecVocMenu.addElement(v);
		v = new Vector<String>(); v.add("New"); v.add("Nouveau");v.add("Novo"); vecVocMenu.addElement(v);
	}
	
	
	public void LoadVocabularyText()
	{
		Vector<String> v = new Vector<String>();
		
		/*0*/v = new Vector<String>(); v.add("Go"); v.add("Vas-y"); v.add("Va"); vecVocTex.add(v);
		/*1*/v = new Vector<String>(); v.add("Welcome."); v.add("Bienvenue."); v.add("Bem-vindo."); vecVocTex.add(v);
		/*2*/v = new Vector<String>(); v.add("Is it your first time ?"); v.add("Est-t-il votre primière fois ?"); v.add("É a sua primeira vez ?"); vecVocTex.add(v);
		/*3*/v = new Vector<String>(); v.add("Click on ("+ getVoc_menu(0) +"-->"+getVoc_menu(1) + ") to create a new linear program."); 
				   v.add("Cliquez en ("+ getVoc_menu(0) +"-->"+getVoc_menu(1) + ") pour créer un nouveau programme.");  
				   v.add("Clique em ("+ getVoc_menu(0) +"-->"+getVoc_menu(1) + ") para criar un novo programa."); 
				   vecVocTex.add(v);
		/*4*/v = new Vector<String>(); v.add("Canonical Formulation"); v.add("Formulation Canonique"); v.add("Formulation Canonica"); vecVocTex.add(v);
		
	}
	
	public int size()
	{
		return vecVocTex.size();
	}

}
