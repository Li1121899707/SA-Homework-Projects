



import java.util.Vector;

public class ShoeInfoVisitor extends Visitor{

   private String[] s = new String[9];

   public String describeShoes() {
	   String infoStr ="\n";
	   for(int n=0; n< 9; n++){
	      if(s[n] != null){
	         infoStr = infoStr + s[n]+"\n";
		  }
	   }
	   return infoStr;
    }

	public void visitAdidas(Adidas e){
	   // write code here. ��s[0]��ֵ
		s[0] = e.getDescription();
	}
	public void visitSalomon(Salomon e){
        // write code here����s[1]��ֵ
		s[1] = e.getDescription();
	}
	public void visitPonyMexico(PonyMexico e){
        // write code here
		s[2] = e.getDescription();
    }
	public void visitNorthFace (NorthFace e){
        // write code here
		s[3] = e.getDescription();
	}
	public void visitNike(Nike e){
        // write code here
		s[4] = e.getDescription();
    }
	public void visitMephisto(Mephisto e){
        // write code here
		s[5] = e.getDescription();
	}
	public void visitGlobeBlitz (GlobeBlitz e){
        // write code here
		s[6] = e.getDescription();
    }
	public void visitGlobeAppleyard(GlobeAppleyard e){
        // write code here
		s[7] = e.getDescription();
	}
    public void visitDCShoesRover(DCShoesRover e){
        // write code here����s[8]��ֵ
    	s[8] = e.getDescription();
	}
}

