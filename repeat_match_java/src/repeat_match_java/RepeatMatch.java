package repeat_match_java;

public final class RepeatMatch {

	static char  Complement  (char Ch)

	/* Returns the DNA complement of  Ch . */
	
	  {
		char lc = Character.toLowerCase(Ch);
	   switch  ( lc )
	     {
	      case  'a' :
	        return  't';
	      case  'c' :
	        return  'g';
	      case  'g' :
	        return  'c';
	      case  't' :
	        return  'a';
	      case  'r' :          // a or g
	        return  'y';
	      case  'y' :          // c or t
	        return  'r';
	      case  's' :          // c or g
	        return  's';
	      case  'w' :          // a or t
	        return  'w';
	      case  'm' :          // a or c
	        return  'k';
	      case  'k' :          // g or t
	        return  'm';
	      case  'b' :          // c, g or t
	        return  'v';
	      case  'd' :          // a, g or t
	        return  'h';
	      case  'h' :          // a, c or t
	        return  'd';
	      case  'v' :          // a, c or g
	        return  'b';
	      default :            // anything
	        return  lc;
	     }
	  }


	static boolean CompareIUPAC (char x, char y)
	{
	  x = Character.toLowerCase(x);
	  y = Character.toLowerCase(y);

	  if ( x == 'n' || x == 'x' || y == 'n' || y == 'x' )
	    return true;

	  switch ( x )
	    {
	    case 'a' :
	      switch ( y )
	        {
	        case 'a':
	        case 'r':
	        case 'w':
	        case 'm':
	        case 'd':
	        case 'h':
	        case 'v':
	          return true;
	        }
	      return false;

	    case 'c' :
	      switch ( y )
	        {
	        case 'c':
	        case 'y':
	        case 's':
	        case 'm':
	        case 'b':
	        case 'h':
	        case 'v':
	          return true;
	        }
	      return false;

	    case 'g' :
	      switch ( y )
	        {
	        case 'g':
	        case 'r':
	        case 's':
	        case 'k':
	        case 'b':
	        case 'd':
	        case 'v':
	          return true;
	        }
	      return false;

	    case 't' :
	      switch ( y )
	        {
	        case 't':
	        case 'y':
	        case 'w':
	        case 'k':
	        case 'b':
	        case 'd':
	        case 'h':
	          return true;
	        }
	      return false;

	    case 'r' :          // a or g
	      switch ( y )
	        {
	        case 'r':
	        case 'a':
	        case 'g':
	        case 'd':
	        case 'v':
	          return true;
	        }
	      return false;

	    case 'y' :          // c or t
	      switch ( y )
	        {
	        case 'y':
	        case 'c':
	        case 't':
	        case 'b':
	        case 'h':
	          return true;
	        }
	      return false;

	    case 's' :          // c or g
	      switch ( y )
	        {
	        case 's':
	        case 'c':
	        case 'g':
	        case 'b':
	        case 'v':
	          return true;
	        }
	      return false;

	    case 'w' :          // a or t
	      switch ( y )
	        {
	        case 'w':
	        case 'a':
	        case 't':
	        case 'd':
	        case 'h':
	          return true;
	        }
	      return false;

	    case 'm' :          // a or c
	      switch ( y )
	        {
	        case 'm':
	        case 'a':
	        case 'c':
	        case 'h':
	        case 'v':
	          return true;
	        }
	      return false;

	    case 'k' :          // g or t
	      switch ( y )
	        {
	        case 'k':
	        case 'g':
	        case 't':
	        case 'b':
	        case 'd':
	          return true;
	        }
	      return false;

	    case 'b' :          // c, g or t
	      switch ( y )
	        {
	        case 'b':
	        case 'c':
	        case 'g':
	        case 't':
	          return true;
	        }
	      return false;

	    case 'd' :          // a, g or t
	      switch ( y )
	        {
	        case 'd':
	        case 'a':
	        case 'g':
	        case 't':
	          return true;
	        }
	      return false;

	    case 'h' :          // a, c or t
	      switch ( y )
	        {
	        case 'h':
	        case 'a':
	        case 'c':
	        case 't':
	          return true;
	        }
	      return false;

	    case 'v' :          // a, c or g
	      switch ( y )
	        {
	        case 'v':
	        case 'a':
	        case 'c':
	        case 'g':
	          return true;
	        }
	      return false;
	    }
	  return false;
	}

	
	static void  Reverse_Complement
    (char S [], int Lo, int Hi)

//  Convert substring  S [Lo .. Hi]  to its Watson-Crick reverse
//  complement

  {
   char  Ch;

   while  (Lo <= Hi)
     {
      Ch = S [Hi];
      S [Hi] = Complement (S [Lo]);
      S [Lo] = Complement (Ch);
      Lo ++;
      Hi --;
     }

   return;
  }


	public static void main(String[] args){
		char[] s = "ATGCGC".toCharArray();
		Reverse_Complement(s, 0, 5);
		System.err.println(s);
	}
}
