package hackerrank;

public class CheckForRepeatedCharacters {
    public boolean containRepeatedCharacter(String value){
        boolean[] arrayCheck = new boolean[128];
        if(value.length() > 128) return true;
        for(int i=0; i<value.length(); i++) {
            int index = value.charAt(i);
            if(arrayCheck[index]) {
                return true;
            } else {
                arrayCheck[index] = true;
            }
        }
        return false;
    }
}
