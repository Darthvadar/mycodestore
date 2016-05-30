package repeat_match_java;

import java.util.LinkedList;
import java.util.List;

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

	/*
	 * 
	 */

	private final static int  NIL = 0;         // Remove if convert to pointers
	private final static int  DEFAULT_MIN_MATCH_LEN = 20;
	private final static char  DOLLAR_CHAR = '$';
	private final static char  DONT_KNOW_CHAR = 'N';
	private final static char  START_CHAR = '%';
	
	public int  opt_Min_Match_Len = DEFAULT_MIN_MATCH_LEN;
	  // set by -n option; 
	public boolean  opt_Exhaustive_Matches = false;
	  // Set by -E option; if true then matches are found by exhaustive search
	  // For testing purposes
	public boolean  opt_Forward_Only = false;
	  // Set by -f option; if true then matches to reverse complement string
	  // are not considered	
	public boolean  opt_Tandem_Only = false;
	  // Set by -t option to output only tandem repeats
	public boolean  opt_Verbose = false;
	  // Set by -V option to do extra tests and/or print debugging output
	
	private int  Global_Trace = 0;
	private int  Global_Skip_Ct = 0;
	private int  Global_Non_Skip_Ct = 0;

	
	private final class Node	  {
	   int  Lo;
	   int  Child = 31;
	   boolean  Child_Is_Leaf = true;
	   int  Sibling = 31;
	   boolean  Sibling_Is_Leaf = true;
	   int  Link;
	   int  Depth;
	   int ID;
	   int  Parent;
	   int  Len = 31;
	   boolean  Should_Skip = true;
	   int  Subtree_Size;
	  } 

	private final class Leaf 	  {
	   int  Lo;
	   int  Sibling = 31;
	   boolean  Sibling_Is_Leaf = true;
	   int  Depth;
	   int ID;
	   int  Parent;
	   int  Len = 31;
	   boolean  Is_Duplicate = true;
	  }
	
	private int Input_Seq_Len = 0;
	private char[] Data; 
	private final List<Leaf> Leaf_Array = new LinkedList<Leaf>();
	private final List<Node> Node_Array = new LinkedList<Node>();
	private final List<Integer> Next_Leaf = new LinkedList<Integer>();
	private int Tree_Root = NIL;
	private int  Curr_ID;
	private int  Curr_String_ID;
	private int  Data_Len = 2;	
	private int  Longest_String = 0;
	private int  Max_Depth = 0;
	private int  Next_Avail_Node = 1;
	private int  Num_Strings = 2;
	private int  String_Separator;
	
	public RepeatMatch(String input_sequence){
		// check sequence?
		// make sure lower case
		String forward_seq = input_sequence.toLowerCase();
		
		Input_Seq_Len = forward_seq.length();		
		
		// use for in situ reverse complement
		char[] _s = forward_seq.toCharArray();
		Reverse_Complement(_s, 0, Input_Seq_Len-1);
		//String revcomp_seq = String.valueOf(_s);
		
		// build string rep for data: 
		// Start_char, input_sequence, dollar_char, reverse_complement, dollar_char, end_char '\0'
		StringBuilder sb = new StringBuilder();
		sb.append(START_CHAR);
		sb.append(forward_seq);
		sb.append(DOLLAR_CHAR);
		sb.append(_s);
		sb.append(DOLLAR_CHAR);
		sb.append('\0');
		int data_store_size = 2 * Input_Seq_Len + 4;
		Data_Len = 3 + 2 * Input_Seq_Len;
		Data = sb.toString().toCharArray();
		
//		System.err.println(sb.length());
//		System.err.println(data_store_size);
//		System.err.println(Data_Len);
//		System.err.println(Data.length);
//		
		String_Separator = 1 + Input_Seq_Len;
		
		for(int i = 0; i< Data_Len; i++){
			Leaf_Array.add(new Leaf());
			Node_Array.add(new Node());
			Next_Leaf.add(-1);
		}
		
		Curr_String_ID = 0;
		Tree_Root = Build_Suffix_Tree (1);
		
		
	}
	
	private int  Add_Duplicates  (int Start, int End, int Leaf, int Leaf_Depth)

	/* Mark all duplicate occurrences of suffixes in  Data [Start .. End]
	*  in suffix tree where the first duplicate is at  Leaf  whose
	*  depth is  Leaf_Depth . */

	  {
	   int  i, j;

	   j = Leaf;
	   for  (i = Start;  i <= End;  i ++, j++)
	     Leaf_Array.get(j).Is_Duplicate = true;

	   return  0;
	  }

	private int  Add_String  (int Start, int Root)

	/* Add all suffixes of string  Data [Start ...]  ending at the
	*  first DOLLAR_CHAR encountered to the suffix tree rooted at
	*  Root . */

	  {
	   int  Leaf, End, Last_Parent, Grandparent;
	   int  Segment_Len, Segment_Start, Leaf_Len;
	   int  Last_Parent_Depth, Link_Depth, Matched, Offset;
	   int  New_Place, New_Depth;
	   boolean New_Place_Is_Leaf, Made_New_Node;

	   for  (End = Start;  Data [End] != DOLLAR_CHAR;  End ++)
	     ;

	   Curr_ID = Start;
	   Segment_Start = Start;
	   Segment_Len = 1 + End - Start;
	   
	   _New_Step_Down_InOut_Args nsdargs = new _New_Step_Down_InOut_Args();
	   
	    New_Step_Down (Root, 0, Segment_Start, Segment_Len, true, nsdargs);
	    New_Place = nsdargs.New_Place;
	    Matched = nsdargs.Depth;
	    Grandparent = nsdargs.Grandparent;
	    New_Place_Is_Leaf = nsdargs.New_Place_Is_Leaf;

	   if  (Segment_Len == Matched)
	       {
	        System.err.printf ("*** Genome is exact palindrome ***\n");
	        return  -1;
	       }

	   if  (1 + Matched == Segment_Len)
	       {
	        Offset = 0;
	        if  (New_Place_Is_Leaf)
	        	System.err.printf ("ERROR:  Unexpected leaf\n");
	          else
	            {
	             while  (! Node_Array.get(New_Place) . Child_Is_Leaf)
	               {
	                New_Place = Node_Array.get(New_Place) . Child;
	                Offset += Math.abs (Node_Array.get(New_Place) . Len);
	               }
	             New_Place = Node_Array.get(New_Place) . Child;
	             Offset += Math.abs (Leaf_Array.get(New_Place) . Len) - 1;
	            }
	        System.err.printf ("String %d is a substring of previous string.\n",
	                     Curr_String_ID);
	        return  0;
	       }

	   Leaf = Start;
	   Leaf_Array.get(Leaf) . Lo = Segment_Start + Matched;
	   Leaf_Array.get(Leaf) . Sibling = Node_Array.get(New_Place) . Child;
	   Leaf_Array.get(Leaf) . Sibling_Is_Leaf = Node_Array.get(New_Place) . Child_Is_Leaf;
	   Node_Array.get(New_Place) . Child = Leaf;
	   Node_Array.get(New_Place) . Child_Is_Leaf = true;
	   Leaf_Array.get(Leaf) . Len = Segment_Len - Matched;
	   Leaf_Array.get(Leaf) . Depth = 1 + End - Start;
	   Leaf_Array.get(Leaf) . ID = Start;
	   Leaf_Array.get(Leaf) . Parent = New_Place;

	   Last_Parent = New_Place;
	   Last_Parent_Depth = Matched;

	   while  (++ Start <= End)
	     {
	      Curr_ID ++;
	      if  (Node_Array.get(Last_Parent) . Link != NIL)
	          {
	           if  (Last_Parent == Root)
	               {
	                Segment_Start = Start;
	                Segment_Len = 1 + End - Start;
	                Link_Depth = Last_Parent_Depth;
	               }
	             else
	               {
	                Segment_Start = Start + Last_Parent_Depth - 1;
	                Segment_Len = 2 + End - Start - Last_Parent_Depth;
	                Link_Depth = Last_Parent_Depth - 1;
	               }
	           New_Step_Down (Node_Array.get(Last_Parent) . Link,
	                                  Link_Depth,
	                                  Segment_Start, Segment_Len,
	                                  false,
	                                  nsdargs);
	            New_Place = nsdargs.New_Place;
		   	    Matched = nsdargs.Depth;
		   	    Grandparent = nsdargs.Grandparent;
		   	    New_Place_Is_Leaf = nsdargs.New_Place_Is_Leaf;
	           if  (Matched == Segment_Len)
	               {
	                New_Depth = Link_Depth + Matched;
	                return  Add_Duplicates (Start, End, New_Place, New_Depth);
	               }

	           Leaf = Start;
	           Leaf_Array.get(Leaf) . Lo = Segment_Start + Matched;
	           Leaf_Array.get(Leaf) . Sibling = Node_Array.get(New_Place) . Child;
	           Leaf_Array.get(Leaf) . Sibling_Is_Leaf
	                  = Node_Array.get(New_Place) . Child_Is_Leaf;
	           Node_Array.get(New_Place) . Child = Leaf;
	           Node_Array.get(New_Place) . Child_Is_Leaf = true;
	           Leaf_Array.get(Leaf) . Len = Segment_Len - Matched;
	           Leaf_Array.get(Leaf) . Depth = 1 + End - Start;
	           Leaf_Array.get(Leaf) . ID = Start;
	           Leaf_Array.get(Leaf) . Parent = New_Place;

	           Last_Parent = New_Place;
	           Last_Parent_Depth = Link_Depth + Matched;
	          }
	        else
	          {
	           Leaf_Len = Leaf_Array.get(Leaf) . Len;
	           if  (Grandparent == Root)
	               {
	                Segment_Start = Start;
	                Segment_Len = Node_Array.get(Last_Parent) . Len - 1;
	                Link_Depth = 0;
	               }
	             else
	               {
	                Segment_Start = Start + Last_Parent_Depth - 1
	                                    - Node_Array.get(Last_Parent) . Len;
	                Segment_Len = Node_Array.get(Last_Parent) . Len;
	                Link_Depth = Last_Parent_Depth - 1
	                                    - Node_Array.get(Last_Parent) . Len;
	               }
	           
	           _New_Jump_Down_InOut_Args njdargs = new _New_Jump_Down_InOut_Args();
	           New_Jump_Down (Node_Array.get(Grandparent) . Link,
	                                  Link_Depth,
	                                  Segment_Start, Segment_Len,
	                                  njdargs);
	           New_Place = njdargs.New_Place;
	           Made_New_Node = njdargs.Made_New_Node;
	           Grandparent = njdargs.Grandparent;

	           if  (Made_New_Node)
	               {
	                Leaf = Start;
	                Leaf_Array.get(Leaf) . Lo = Segment_Start + Segment_Len;
	                Leaf_Array.get(Leaf) . Sibling = Node_Array.get(New_Place) . Child;
	                Leaf_Array.get(Leaf) . Sibling_Is_Leaf
	                       = Node_Array.get(New_Place) . Child_Is_Leaf;
	                Node_Array.get(New_Place) . Child = Leaf;
	                Node_Array.get(New_Place) . Child_Is_Leaf = true;
	                Leaf_Array.get(Leaf) . Len = Leaf_Len;
	                Leaf_Array.get(Leaf) . Depth = 1 + End - Start;
	                Leaf_Array.get(Leaf) . ID = Start;
	                Leaf_Array.get(Leaf) . Parent = New_Place;
	                Node_Array.get(Last_Parent) . Link = New_Place;
	                Last_Parent = New_Place;
	                Last_Parent_Depth = Link_Depth + Segment_Len;
	               }
	             else
	               {
	                Node_Array.get(Last_Parent) . Link = New_Place;
	                Segment_Start += Segment_Len;
	                Link_Depth += Segment_Len;
	                Segment_Len = Leaf_Len;
	                New_Step_Down (New_Place, Link_Depth,
	                                       Segment_Start, Segment_Len,
	                                       false,
	                                       nsdargs);
	                New_Place = nsdargs.New_Place;
			   	    Matched = nsdargs.Depth;
			   	    Grandparent = nsdargs.Grandparent;
			   	    New_Place_Is_Leaf = nsdargs.New_Place_Is_Leaf;
	                if  (Matched >= Segment_Len)
	                    {
	                     New_Depth = Link_Depth + Matched;
	                     return  Add_Duplicates (Start, End, New_Place, New_Depth);
	                    }

	                Leaf = Start;
	                Leaf_Array.get(Leaf) . Lo = Segment_Start + Matched;
	                Leaf_Array.get(Leaf) . Sibling
	                        = Node_Array.get(New_Place) . Child;
	                Leaf_Array.get(Leaf) . Sibling_Is_Leaf
	                       = Node_Array.get(New_Place) . Child_Is_Leaf;
	                Node_Array.get(New_Place) . Child = Leaf;
	                Node_Array.get(New_Place) . Child_Is_Leaf = true;
	                Leaf_Array.get(Leaf) . Len = Segment_Len - Matched;
	                Leaf_Array.get(Leaf) . Depth = 1 + End - Start;
	                Leaf_Array.get(Leaf) . ID = Start;
	                Leaf_Array.get(Leaf) . Parent = New_Place;
	                Last_Parent = New_Place;
	                Last_Parent_Depth = Link_Depth + Matched;
	               }
	          }
	     }

	   return  0;
	  }

	private final class _New_Step_Down_InOut_Args {
		int New_Place;
		int Depth;
		boolean New_Place_Is_Leaf;
		int Grandparent;
		
		public _New_Step_Down_InOut_Args(){
			
		}
		
		public _New_Step_Down_InOut_Args(int New_Place, int Depth, boolean New_Place_Is_Leaf, int Grandparent){
			this();
			this.New_Place = New_Place;
			this.Depth = Depth;
			this.New_Place_Is_Leaf = New_Place_Is_Leaf;
			this.Grandparent = Grandparent;
		}
	}
	
	private void  New_Step_Down
    (int Node, int Node_Depth, int Lo, int Len, boolean Doing_Prefix,
    		_New_Step_Down_InOut_Args inout_args)

/* Return the subscript of the node that represents the lowest
*  descendant of  Node  that matches  Data [Lo .. Lo + Len - 1] .
*  If necessary, allocate a new node and return its subscript,
*  unless  Doing_Prefix .  In that case do not allocate a new node
*  if  1 + Depth == Len  but return the child of where the new
*  node would be allocated.  Set  Depth  to the number of characters
*  successfully matched from  Node  down to the returned  Node .
*  Node_Depth  is the depth of  Node  in the suffix tree.
*  Set  Return_Is_Leaf  to indicate the status of the returned
*  subscript.  If a new node is allocated, set  Par_New_Node
*  to its parent.
*/

  {
   int  P, Q, i, j, D, P_Sib;
   boolean P_Sib_Is_Leaf;

   inout_args.Depth = 0;

   if  (Len == 0){
	   inout_args.New_Place = NIL;
       return;
   }

   _New_Find_Child_InOut_Args nfcargs = new _New_Find_Child_InOut_Args();
   New_Find_Child (Node, Data [Lo], Node_Depth, nfcargs);
   P = nfcargs.P;

   while  (P != NIL)
     {
      if  (nfcargs.P_Is_Leaf)
          {
           i = Leaf_Array.get(P) . Lo;
           D = Leaf_Array.get(P) . Len;
           P_Sib = Leaf_Array.get(P) . Sibling;
           P_Sib_Is_Leaf = Leaf_Array.get(P) . Sibling_Is_Leaf;
          }
        else
          {
           i = Node_Array.get(P) . Lo;
           D = Node_Array.get(P) . Len;
           P_Sib = Node_Array.get(P) . Sibling;
           P_Sib_Is_Leaf = Node_Array.get(P) . Sibling_Is_Leaf;
          }
      for  (j = 1;  j < Len && j < D
                      && Data [i + j] == Data [Lo + j];
                 j ++)
        ;
      inout_args.Depth += j;

      if  (j < D)
          {
           if  (Doing_Prefix && 1 + j == Len)
               {
        	   inout_args.New_Place_Is_Leaf = nfcargs.P_Is_Leaf;
        	   inout_args.New_Place = P;
                return;
               }
           Q = New_Node ();
           Node_Array.get(Q) . Lo = Lo;
           Node_Array.get(Q) . Len = j;
           Node_Array.get(Q) . Child = P;
           Node_Array.get(Q) . Sibling = P_Sib;
           Node_Array.get(Q) . Sibling_Is_Leaf = P_Sib_Is_Leaf;
           inout_args.Grandparent = Node;
           Node_Array.get(Q) . Link = NIL;
           Node_Array.get(Q) . Child_Is_Leaf = nfcargs.P_Is_Leaf;
           Node_Array.get(Q) . Parent = Node;
           Node_Array.get(Q) . Depth = Node_Array.get(Node) . Depth + j;
           Node_Array.get(Q) . ID = Curr_ID;
           if  (nfcargs.Pred == NIL)
               {
                Node_Array.get(Node) . Child = Q;
                Node_Array.get(Node) . Child_Is_Leaf = false;
               }
           else if  (nfcargs.Pred_Is_Leaf)
               {
                Leaf_Array.get(nfcargs.Pred) . Sibling = Q;
                Leaf_Array.get(nfcargs.Pred) . Sibling_Is_Leaf = false;
               }
             else
               {
                Node_Array.get(nfcargs.Pred) . Sibling = Q;
                Node_Array.get(nfcargs.Pred) . Sibling_Is_Leaf = false;
               }

           if  (! nfcargs.P_Is_Leaf)
               {
                Node_Array.get(P) . Lo += j;
                Node_Array.get(P) . Len -= j;
                Node_Array.get(P) . Parent = Q;
                Node_Array.get(P) . Sibling = NIL;
                Node_Array.get(P) . Sibling_Is_Leaf = false;
               }
             else
               {
                Leaf_Array.get(P) . Lo += j;
                Leaf_Array.get(P) . Len -= j;
                Leaf_Array.get(P) . Parent = Q;
                Leaf_Array.get(P) . Sibling = NIL;
                Leaf_Array.get(P) . Sibling_Is_Leaf = false;
               }
           
           inout_args.New_Place_Is_Leaf = false;
           inout_args.New_Place = Q;
           return;
          }

      if  (nfcargs.P_Is_Leaf || j == Len)
          {
//           Return_Is_Leaf = true;
    	  inout_args.New_Place_Is_Leaf = nfcargs.P_Is_Leaf;
    	  inout_args.New_Place = P;
           return;
          }

      Lo += j;
      Len -= j;
      Node = P;
      Node_Depth += j;
      New_Find_Child (Node, Data [Lo], Node_Depth, nfcargs);
      P = nfcargs.P;
     }

   inout_args.New_Place_Is_Leaf = false;
   inout_args.New_Place = Node;
   return ;
  }
	
	private int  Build_Suffix_Tree  (int Start)

	/* Build a suffix tree for the string  Data [Start ...]  ending at
	*  the first DOLLAR_CHAR encountered.  Return the subscript of the
	*  root of the resulting tree. */

	  {
	   int  Root, Leaf, End, Last_Parent = NIL, Grandparent = NIL;
	   int  Segment_Len, Segment_Start, Leaf_Len;
	   int  Last_Parent_Depth, Link_Depth, Matched;
	   int  New_Place ;
	   boolean New_Place_Is_Leaf, Made_New_Node;

	   for  (End = Start;  Data [End] != DOLLAR_CHAR;  End ++)
	     ;

	   Curr_ID = Start;
	   Root = New_Node ();
	   Leaf = Start;

	   Node_Array.get(Root) . Lo = 0;
	   Node_Array.get(Root) . Child = Leaf;
	   Node_Array.get(Root) . Sibling = NIL;
	   Node_Array.get(Root) . Link = Root;
	   Node_Array.get(Root) . Len = 0;
	   Node_Array.get(Root) . Sibling_Is_Leaf = false;
	   Node_Array.get(Root) . Child_Is_Leaf = true;
	   Node_Array.get(Root) . Depth = 0;
	   Node_Array.get(Root) . ID = Curr_ID;
	   Node_Array.get(Root) . Parent = NIL;

	   Leaf_Array.get(Leaf) . Lo = Start;
	   Leaf_Array.get(Leaf) . Sibling = NIL;
	   Leaf_Array.get(Leaf) . Len = 1 + End - Start;
	   Leaf_Array.get(Leaf) . Sibling_Is_Leaf = false;
	   Leaf_Array.get(Leaf) . Depth = 1 + End - Start;
	   Leaf_Array.get(Leaf) . ID = Start;
	   Leaf_Array.get(Leaf) . Parent = Root;

	   Last_Parent = Root;
	   Last_Parent_Depth = 0;

	   while  (++ Start <= End)
	     {
	      if  (opt_Verbose)
	          System.out.printf ("Build_Suffix_Tree:  Start = %d\n", Start);

	      Curr_ID ++;
	      if  (Node_Array.get(Last_Parent) . Link != NIL)
	          {
	           if  (Last_Parent == Root)
	               {
	                Segment_Start = Start;
	                Segment_Len = 1 + End - Start;
	                Link_Depth = Last_Parent_Depth;
	               }
	             else
	               {
	                Segment_Start = Start + Last_Parent_Depth - 1;
	                Segment_Len = 2 + End - Start - Last_Parent_Depth;
	                Link_Depth = Last_Parent_Depth - 1;
	               }
	           _New_Step_Down_InOut_Args nsdargs = new _New_Step_Down_InOut_Args();
	            New_Step_Down (Node_Array.get(Last_Parent) . Link,
	                                  Link_Depth,
	                                  Segment_Start, Segment_Len,
	                                  false,
	                                  nsdargs);
	            New_Place = nsdargs.New_Place;
	   	    	Matched = nsdargs.Depth;
	   	    	Grandparent = nsdargs.Grandparent;
	   	    	New_Place_Is_Leaf = nsdargs.New_Place_Is_Leaf;
	           
	           if  (Matched >= Segment_Len)
	               {
	        	   System.err.printf ("Ooops:  Suffix can't appear twice.\n");
	        	   System.exit  (1);
	               }
	             else
	               {
	                Leaf = Start;
	                Leaf_Array.get(Leaf) . Lo = Segment_Start + Matched;
	                Leaf_Array.get(Leaf) . Sibling = Node_Array.get(New_Place) . Child;
	                Leaf_Array.get(Leaf) . Sibling_Is_Leaf
	                       = Node_Array.get(New_Place) . Child_Is_Leaf;
	                Node_Array.get(New_Place) . Child = Leaf;
	                Node_Array.get(New_Place) . Child_Is_Leaf = true;
	                Leaf_Array.get(Leaf) . Len = Segment_Len - Matched;
	                Leaf_Array.get(Leaf) . Depth = 1 + End - Start;
	                Leaf_Array.get(Leaf) . ID = Start;
	                Leaf_Array.get(Leaf) . Parent = New_Place;
	               }
	           Last_Parent = New_Place;
	           Last_Parent_Depth = Link_Depth + Matched;
	          }
	        else
	          {
	           Leaf_Len = Leaf_Array.get(Leaf) . Len;
	           if  (Grandparent == Root)
	               {
	                Segment_Start = Start;
	                Segment_Len = Node_Array.get(Last_Parent) . Len - 1;
	                Link_Depth = 0;
	               }
	             else
	               {
	                Segment_Start = Start + Last_Parent_Depth - 1
	                                    - Node_Array.get(Last_Parent) . Len;
	                Segment_Len = Node_Array.get(Last_Parent) . Len;
	                Link_Depth = Last_Parent_Depth - 1
	                                    - Node_Array.get(Last_Parent) . Len;
	               }

	           _New_Jump_Down_InOut_Args njdargs = new _New_Jump_Down_InOut_Args();
	           New_Jump_Down (Node_Array.get(Grandparent) . Link,
	                                  Link_Depth,
	                                  Segment_Start, Segment_Len,
	                                  njdargs);
	            New_Place = njdargs.New_Place;
	            Made_New_Node = njdargs.Made_New_Node;
	   	    	Grandparent = njdargs.Grandparent;
	           
	           if  (Made_New_Node)
	               {
	                Leaf = Start;
	                Leaf_Array.get(Leaf) . Lo = Segment_Start + Segment_Len;
	                Leaf_Array.get(Leaf) . Sibling = Node_Array.get(New_Place) . Child;
	                Leaf_Array.get(Leaf) . Sibling_Is_Leaf = Node_Array.get(New_Place) . Child_Is_Leaf;
	                Node_Array.get(New_Place) . Child = Leaf;
	                Node_Array.get(New_Place) . Child_Is_Leaf = true;
	                Leaf_Array.get(Leaf) . Len = Leaf_Len;
	                Leaf_Array.get(Leaf) . Depth = 1 + End - Start;
	                Leaf_Array.get(Leaf) . ID = Start;
	                Leaf_Array.get(Leaf) . Parent = New_Place;
	                Node_Array.get(Last_Parent) . Link = New_Place;
	                Last_Parent = New_Place;
	                Last_Parent_Depth = Link_Depth + Segment_Len;
	               }
	             else
	               {
	                Node_Array.get(Last_Parent) . Link = New_Place;
	                Segment_Start += Segment_Len;
	                Link_Depth += Segment_Len;
	                Segment_Len = Leaf_Len;
	                _New_Step_Down_InOut_Args nsdargs = new _New_Step_Down_InOut_Args();
	                New_Step_Down (New_Place, Link_Depth,
	                                       Segment_Start, Segment_Len,
	                                       false,
	                                       nsdargs);
	                New_Place = nsdargs.New_Place;
		   	    	Matched = nsdargs.Depth;
		   	    	Grandparent = nsdargs.Grandparent;
		   	    	New_Place_Is_Leaf = nsdargs.New_Place_Is_Leaf;
		   	    	
	                if  (Matched >= Segment_Len)
	                    {
	                     System.err.printf ("Ooops:  Suffix can't appear twice.\n");
	                     System.exit  (1);
	                    }
	                  else
	                    {
	                     Leaf = Start;
	                     Leaf_Array.get(Leaf) . Lo = Segment_Start + Matched;
	                     Leaf_Array.get(Leaf) . Sibling
	                             = Node_Array.get(New_Place) . Child;
	                     Leaf_Array.get(Leaf) . Sibling_Is_Leaf
	                            = Node_Array.get(New_Place) . Child_Is_Leaf;
	                     Node_Array.get(New_Place) . Child = Leaf;
	                     Node_Array.get(New_Place) . Child_Is_Leaf = true;
	                     Leaf_Array.get(Leaf) . Len = Segment_Len - Matched;
	                     Leaf_Array.get(Leaf) . Depth = 1 + End - Start;
	                     Leaf_Array.get(Leaf) . ID = Start;
	                     Leaf_Array.get(Leaf) . Parent = New_Place;
	                    }
	                Last_Parent = New_Place;
	                Last_Parent_Depth = Link_Depth + Matched;
	               }
	          }
	     }

	   return  Root;
	  }

	private final class _New_Find_Child_InOut_Args {
		int P;
		boolean P_Is_Leaf;
		int Pred;
		boolean Pred_Is_Leaf;
		
		public _New_Find_Child_InOut_Args(){
			
		}
		public _New_Find_Child_InOut_Args(int P, boolean P_Is_Leaf, int Pred, boolean Pred_Is_Leaf){
			this();
			this.P = P;
			this.Pred_Is_Leaf = P_Is_Leaf;
			this.Pred = Pred;
			this.Pred_Is_Leaf = Pred_Is_Leaf;
		}
	}
	
	private void  New_Find_Child
	    (int Node, char Ch, int Node_Depth,
	    		_New_Find_Child_InOut_Args inout_args)

	/* Return the subscript of child of  Node  whose string starts
	*  with  Ch  and set  Is_Leaf  to indicate what kind of node
	*  that child is.  Set  Pred  to the subscript of the prior
	*  sibling of the chosen child and  Pred_Is_Leaf  to indicate
	*  whether  Pred  is a leaf or not.   Node_Depth  is the depth of  Node
	*  in the tree.
	*/

	  {
	   int  Leaf_Lo, Leaf_Len;
	   int  i;
	   char  Start_Ch;

	   inout_args.Pred = NIL;
	   inout_args.Pred_Is_Leaf = false;
	   i = Node_Array.get(Node) . Child;
	   inout_args.P_Is_Leaf = Node_Array.get(Node) . Child_Is_Leaf;
	   if  (inout_args.P_Is_Leaf)
	       {
	        Leaf_Lo = Leaf_Array.get(i) . Lo;
	        Leaf_Len = Leaf_Array.get(i) . Len;
	        if  (Leaf_Len > 0)
	            Start_Ch = Data [Leaf_Lo];
	          else
	            Start_Ch = Complement (Data [Leaf_Lo]);
	       }
	     else
	       {
	        if  (Node_Array.get(i) . Len > 0)
	            Start_Ch = Data [Node_Array.get(i) . Lo];
	          else
	            Start_Ch = Complement (Data [Node_Array.get(i) . Lo]);
	       }

	   while  (i != NIL && Start_Ch != Ch)
	     {
		   inout_args.Pred = i;
		   inout_args.Pred_Is_Leaf = inout_args.P_Is_Leaf;
	      if  (inout_args.P_Is_Leaf)
	          {
	    	  inout_args.P_Is_Leaf = Leaf_Array.get(i) . Sibling_Is_Leaf;
	           i = Leaf_Array.get(i) . Sibling;
	          }
	        else
	          {
	        	inout_args.P_Is_Leaf = Node_Array.get(i) . Sibling_Is_Leaf;
	           i = Node_Array.get(i) . Sibling;
	          }
	      if  (inout_args.P_Is_Leaf)
	          {
	           Leaf_Lo = Leaf_Array.get(i) . Lo;
	           Leaf_Len = Leaf_Array.get(i) . Len;
	           if  (Leaf_Len > 0)
	               Start_Ch = Data [Leaf_Lo];
	             else
	               Start_Ch = Complement (Data [Leaf_Lo]);
	          }
	        else
	          {
	           if  (Node_Array.get(i) . Len > 0)
	               Start_Ch = Data [Node_Array.get(i) . Lo];
	             else
	               Start_Ch = Complement (Data [Node_Array.get(i) . Lo]);
	          }
	     }

	   inout_args.P = i;
	   
	   return;
	  }

	private final class _New_Jump_Down_InOut_Args {
		int New_Place;
		boolean Made_New_Node;
		int Grandparent;		
		
		public _New_Jump_Down_InOut_Args(){
			
		}
		
		public _New_Jump_Down_InOut_Args(int New_Place, boolean Made_New_Node, int Grandparent){
			this();
			this.New_Place = New_Place;
			this.Made_New_Node = Made_New_Node;
			this.Grandparent = Grandparent;
		}
	}

	private void  New_Jump_Down
	    (int Node, int Node_Depth, int Lo, int Len, 
	    		_New_Jump_Down_InOut_Args inout_args)

	/* Return the subscript of the node that represents the
	*  descendant of  Node  that matches  Data [Lo .. Lo + Len - 1] .
	*  Check only the first character of each child substring--the
	*  rest are assumed to match automatically.
	*  Node_Depth  is the depth of  Node  in the suffix tree.
	*  If necessary, allocate a new node and return its subscript.
	*  Set  Made_New_Node  to true if a new node was allocated and
	*  set  Par_New_Node  to the parent of that new node.
	*/

	  {
	   int P, Q, D, P_Sib;
	   boolean P_Sib_Is_Leaf;

	   inout_args.Made_New_Node = false;
	   if  (Len == 0){
		   inout_args.New_Place = Node;
		   return;
	   }

	   if  (opt_Verbose)
	       System.out.printf ("Jump_Down:  Node = %d  Depth = %d  Lo = %d  Len = %d\n",
	               Node, Node_Depth, Lo, Len);

	   _New_Find_Child_InOut_Args nfcargs = new _New_Find_Child_InOut_Args();
	   New_Find_Child (Node, Data [Lo], Node_Depth, nfcargs  );
	   P = nfcargs.P;
	   
	   while  (P != NIL)
	     {
	      if  (nfcargs.P_Is_Leaf)
	          {
	           D = Leaf_Array.get(P) . Len;
	           P_Sib = Leaf_Array.get(P) . Sibling;
	           P_Sib_Is_Leaf = Leaf_Array.get(P) . Sibling_Is_Leaf;
	          }
	        else
	          {
	           D = Node_Array.get(P) . Len;
	           P_Sib = Node_Array.get(P) . Sibling;
	           P_Sib_Is_Leaf = Node_Array.get(P) . Sibling_Is_Leaf;
	          }

	      if  (Len == D){
	    	  inout_args.New_Place = P;
	    	  return;
	      }

	      if  (D < Len)
	          {
	           Lo += D;
	           Len -= D;
	           Node = P;
	           Node_Depth += D;
	           New_Find_Child (Node, Data [Lo], Node_Depth, nfcargs);
	           P = nfcargs.P;
	          }
	        else
	          {
	           Q = New_Node ();
	           Node_Array.get(Q) . Lo = Lo;
	           Node_Array.get(Q) . Len = Len;
	           Node_Array.get(Q) . Child = P;
	           Node_Array.get(Q) . Sibling = P_Sib;
	           Node_Array.get(Q) . Sibling_Is_Leaf = P_Sib_Is_Leaf;
	           inout_args.Grandparent = Node;
	           Node_Array.get(Q) . Link = NIL;
	           Node_Array.get(Q) . Child_Is_Leaf = nfcargs.P_Is_Leaf;
	           Node_Array.get(Q) . Parent = Node;
	           Node_Array.get(Q) . Depth = Node_Array.get(Node) . Depth + Len;
	           Node_Array.get(Q) . ID = Curr_ID;

	           if  (nfcargs.Pred == NIL)
	               {
	                Node_Array.get(Node) . Child = Q;
	                Node_Array.get(Node) . Child_Is_Leaf = false;
	               }
	           else if  (nfcargs.Pred_Is_Leaf)
	               {
	                Leaf_Array.get(nfcargs.Pred) . Sibling = Q;
	                Leaf_Array.get(nfcargs.Pred) . Sibling_Is_Leaf = false;
	               }
	             else
	               {
	                Node_Array.get(nfcargs.Pred) . Sibling = Q;
	                Node_Array.get(nfcargs.Pred) . Sibling_Is_Leaf = false;
	               }

	           if  (! nfcargs.P_Is_Leaf)
	               {
	                Node_Array.get(P) . Lo += Len;
	                Node_Array.get(P) . Len -= Len;
	                Node_Array.get(P) . Parent = Q;
	                Node_Array.get(P) . Sibling = NIL;
	                Node_Array.get(P) . Sibling_Is_Leaf = false;
	               }
	             else
	               {
	                Leaf_Array.get(P) . Lo += Len;
	                Leaf_Array.get(P) . Len -= Len;
	                Leaf_Array.get(P) . Parent = Q;
	                Leaf_Array.get(P) . Sibling = NIL;
	                Leaf_Array.get(P) . Sibling_Is_Leaf = false;
	               }
	           
	           inout_args.Made_New_Node = true;
	           inout_args.New_Place = Q;
	           return;
	          }

	     }

	   System.err.printf ("Ooops:  Couldn't find appropriate child node\n");
	   System.exit  (1);

	   inout_args.New_Place = 0;
	   return;
	  }

	private void  List_Matches
	    (int A, int B, int n)

	/* List all maximal matches of length  n   between entries in
	*  the list starting at
	*  subscript  A  in array  Next_Leaf  and the list starting
	*  at subscript  B  in the same array.  Don't list a match
	*  if it's already been listed in a different order (because
	*  of the reverse complement strand. */

	  {
	   int  i, j, k, L, R;
	   boolean Reversed;
	  
	   for  (i = A;  i != NIL;  i = Next_Leaf.get(i))
	     {
	      for  (j = B;  j != NIL;  j = Next_Leaf.get(j))
	        {
	         if  (Data [i - 1] == Data [j - 1]
	                  || Data [i + n] == Data [j + n]
	                  || i > String_Separator && j > String_Separator)
	             continue;
	         Reversed = false;
	         if  (j > String_Separator)
	             {
	              k = Input_Seq_Len - (j - String_Separator) - n + 2;
	              if  (k < i)
	                  continue;
	              L = i;
	              R = k + n - 1;
	              Reversed = true;
	             }
	         else if  (i > String_Separator)
	             {
	              k = Input_Seq_Len - (i - String_Separator) - n + 2;
	              if  (k < j)
	                  continue;
	              L = j;
	              R = k + n - 1;
	              Reversed = true;
	             }
	         else if  (i < j)
	             {
	              L = i;
	              R = j;
	             }
	           else
	             {
	              L = j;
	              R = i;
	             }
	         if  (opt_Verbose)
	             Verify_Match (L, R, n, Reversed);
	         if  (opt_Tandem_Only && L + n < R)
	             continue;
	         System.out.printf ("%9d %10d%c %8d\n", L, R, Reversed ? 'r' : ' ', n);
	        }
	     }
	  }



	private int  List_Tree  (int Root, boolean Is_Leaf, int Parent, int Parent_Depth)

	//  Show contents of suffix tree rooted at  Root  whose parent's
	//  string depth in the suffix tree is  Depth .   Is_Leaf
	//  indicates whether  Root  is a leaf node or not.
	//   Parent  is the subscript of this node's parent.

	  {
	   int  i, j;

	   while  (Root != NIL)
	     if  (Is_Leaf)
	         {
	          if  (Leaf_Array.get(Root) . Len > 0)
	              {
	               j = Leaf_Array.get(Root) . Lo - Parent_Depth;
	               System.out.printf ("Leaf %d:  ", j);
	               for  (i = 0;  i < Leaf_Array.get(Root) . Len;  i ++)
	            	   System.out.print (Data [Leaf_Array.get(Root) . Lo + i]);
	              }
	            else
	              {
	               j = - (Leaf_Array.get(Root) . Lo + Parent_Depth);
	               System.out.printf ("Leaf %d:  ", j);
	               for  (i = 0;  i > Leaf_Array.get(Root) . Len;  i --)
	            	   System.out.print (Complement (Data [Leaf_Array.get(Root) . Lo + i]));
	              }
	          System.out.printf ("  Par = %d", Parent);
	          System.out.printf ("  Depth = %d", Leaf_Array.get(Root) . Depth);
	          if  (Leaf_Array.get(Root) . Is_Duplicate)
	        	  System.out.printf ("  Duplicate = %d", (Root + Input_Seq_Len + 1));
	          System.out.print ('\n');
	          Is_Leaf = Leaf_Array.get(Root) . Sibling_Is_Leaf;
	          Root = Leaf_Array.get(Root) . Sibling;
	         }

	       else
	         {
	    	   System.out.printf ("Node %d:  ID %d  ", Root, Node_Array.get(Root) . ID);
	          
	          if  (Node_Array.get(Root) . Len > 0)
	              for  (i = 0;  i < Node_Array.get(Root) . Len;  i ++)
	                System.out.print (Data [Node_Array.get(Root) . Lo + i]);
	            else
	              for  (i = 0;  i > Node_Array.get(Root) . Len;  i --)
	                System.out.print (Complement (Data [Node_Array.get(Root) . Lo + i]));
	          System.out.printf ("  Par = %d", Parent);

	          System.out.printf ("  Depth = %d", Node_Array.get(Root) . Depth);
	          if  (Node_Array.get(Root) . Link != NIL)
	        	  System.out.printf ("   Link to node %d (ID %d)", Node_Array.get(Root) . Link,
	                      Node_Array.get(Node_Array.get(Root) . Link) . ID);

	          System.out.print ('\n');
	          List_Tree (Node_Array.get(Root) . Child,
	                            Node_Array.get(Root) . Child_Is_Leaf, Root,
	                            Parent_Depth + Math.abs (Node_Array.get(Root) . Len));
	          Is_Leaf = Node_Array.get(Root) . Sibling_Is_Leaf;
	          Root = Node_Array.get(Root) . Sibling;
	         }

	   return  0;
	  }

	private void  List_Maximal_Matches  (int Root, boolean Is_Leaf, int Parent, int Parent_Depth)

	/* List substring pairs that occur more than once where the match
	*  does not extend either to the left or to the right for
	*  the subtree rooted at  Root .  Is_Leaf  indicates
	*  whether  Root  is a leaf or internal node.   Parent  is the
	*  parent of  Root  and  Parent_Depth  is its string-depth in the
	*  suffix tree. */

	  {
	   int  j, k, Depth, List1, Start, End;
	   boolean  k_Is_Leaf;

	   if  (Root == NIL)
	       return;

	   if  (Is_Leaf)
	       {
	        // Skip any match here, it's other version at the start of
	        // the string will be output instead

	        List_Maximal_Matches (Leaf_Array.get(Root) . Sibling,
	               Leaf_Array.get(Root) . Sibling_Is_Leaf,
	               Parent, Parent_Depth);

	        return;
	       }

	   Depth = Parent_Depth + Node_Array.get(Root) . Len;

	   List_Maximal_Matches (Node_Array.get(Root) . Child,
	          Node_Array.get(Root) . Child_Is_Leaf, Root, Depth);

	   List_Maximal_Matches (Node_Array.get(Root) . Sibling,
	          Node_Array.get(Root) . Sibling_Is_Leaf, Parent, Parent_Depth);

	   if  (opt_Verbose)
	       System.out.printf ("List_Maximal_Matches:  Root = %d  Parent = %d  Parent_Depth = %d\n  Depth = %d  Subtree_Size = %d\n",
	               Root, Parent, Parent_Depth, Depth,
	               Node_Array.get(Root) . Subtree_Size);

	if  (Depth >= opt_Min_Match_Len)
	    {
	     if  (Node_Array.get(Root) . Should_Skip)
	         Global_Skip_Ct ++;
	       else
	         Global_Non_Skip_Ct ++;
	    }
	   if  (Depth >= opt_Min_Match_Len
	          && ! Node_Array.get(Root) . Should_Skip)
	       {
	        Is_Leaf = Node_Array.get(Root) . Child_Is_Leaf;
	        for  (j = Node_Array.get(Root) . Child;  j != NIL; )
	          {
	           if  (opt_Verbose)
	               System.out.printf ("  Child = %d %s\n", j, Is_Leaf ? "leaf" : "node");
	           if  (Is_Leaf)
	               {
	                List1 = j;
	                k = Leaf_Array.get(j) . Sibling;
	                k_Is_Leaf = Leaf_Array.get(j) . Sibling_Is_Leaf;
	                Is_Leaf = Leaf_Array.get(j) . Sibling_Is_Leaf;
	                j = Leaf_Array.get(j) . Sibling;
	               }
	             else
	               {
	                List1 = Node_Array.get(j) . Link;
	                k = Node_Array.get(j) . Sibling;
	                k_Is_Leaf = Node_Array.get(j) . Sibling_Is_Leaf;
	                Is_Leaf = Node_Array.get(j) . Sibling_Is_Leaf;
	                j = Node_Array.get(j) . Sibling;
	               }
	           while  (k != NIL)
	             {
	              if  (k_Is_Leaf)
	                  {
	                   List_Matches (List1, k, Depth);
	                   k_Is_Leaf = Leaf_Array.get(k) . Sibling_Is_Leaf;
	                   k = Leaf_Array.get(k) . Sibling;
	                  }
	                else
	                  {
	                   List_Matches (List1, Node_Array.get(k) . Link, Depth);
	                   k_Is_Leaf = Node_Array.get(k) . Sibling_Is_Leaf;
	                   k = Node_Array.get(k) . Sibling;
	                  }
	             }
//	#if  0
//	           if  (Is_Leaf)
//	               {
//	                printf ("  Lf%7d\n", j);
//	                Is_Leaf = Leaf_Array.get(j) . Sibling_Is_Leaf;
//	                j = Leaf_Array.get(j) . Sibling;
//	               }
//	             else
//	               {
//	                printf ("  Nd%7d\n", j);
//	                Is_Leaf = Node_Array.get(j) . Sibling_Is_Leaf;
//	                j = Node_Array.get(j) . Sibling;
//	               }
//	#endif
	          }
	       }

	   Start = End = 0;
	   Is_Leaf = Node_Array.get(Root) . Child_Is_Leaf;
	   for  (j = Node_Array.get(Root) . Child;  j != NIL; )
	     {
	      if  (Is_Leaf)
	          {
	           if  (Start == 0)
	               Start = End = j;
	             else
	               {
	                Next_Leaf.set(End, j);
	                End = j;
	               }
	           Is_Leaf = Leaf_Array.get(j) . Sibling_Is_Leaf;
	           j = Leaf_Array.get(j) . Sibling;
	          }
	        else
	          {
	           if  (Start == 0)
	               Start = End = Node_Array.get(j) . Link;
	             else
	               Next_Leaf.set(End, Node_Array.get(j) . Link );
	           while  (Next_Leaf.get(End) != NIL)
	             End = Next_Leaf.get(End);
	           Is_Leaf = Node_Array.get(j) . Sibling_Is_Leaf;
	           j = Node_Array.get(j) . Sibling;
	          }
	     }
	   Node_Array.get(Root) . Link = Start;
//	#if  0
//	   printf  ("  Leaves: ");
//	   for  (j = Node_Array.get(Root) . Link;  j != NIL;  j = Next_Leaf [j])
//	     printf (" %3d", j);
//	   printf ("\n");
//	#endif

	   return;
	  }



	private int  Longest_Prefix_Match
	    (char[] p, char[] q)

	//  Return the length of the longest common prefix of strings  p
	//  and  q .  Assumes they will mismatch before running off the
	//  end of either string.

	  {
	   int  i;

	   for  (i = 0;  p [i] == q [i];  i ++)
	     ;

	   return  i;
	  }



	private void  Mark_Skipable_Nodes
	    (int Root, boolean Is_Leaf, int Parent, int Parent_Depth)

	//  Set the  Should_Skip  field of the node that  Root  links to
	//  if its  Subtree_Size  is the same as  Root 's,
	//  and do the same recursively in  Root 's subtree.
	//   Is_Leaf  indicates whether  Root  is a leaf or internal node.
	//   Parent  is the parent of  Root  and  Parent_Depth  is its
	//  string-depth in the suffix tree.

	  {
	   int  Link, Depth;

	   if  (Root == NIL)
	       return;

	   if  (Is_Leaf)
	       {
	        Mark_Skipable_Nodes (Leaf_Array.get(Root) . Sibling,
	               Leaf_Array.get(Root) . Sibling_Is_Leaf,
	               Parent, Parent_Depth);

	        return;
	       }

	   Depth = Parent_Depth + Node_Array.get(Root) . Len;

	   Mark_Skipable_Nodes (Node_Array.get(Root) . Child, Node_Array.get(Root) . Child_Is_Leaf,
	          Root, Depth);

	   Mark_Skipable_Nodes (Node_Array.get(Root) . Sibling, Node_Array.get(Root) . Sibling_Is_Leaf,
	          Parent, Parent_Depth);

	   if  (opt_Verbose)
	       System.out.printf ("Mark_Skipable_Nodes:  Root = %d  Parent = %d  Parent_Depth = %d\n",
	               Root, Parent, Parent_Depth);

	   Link = Node_Array.get(Root) . Link;
	   if  (Link != NIL
	          && Node_Array.get(Link) . Subtree_Size == Node_Array.get(Root) . Subtree_Size)
	        Node_Array.get(Link) . Should_Skip = true;

	   // Will mark the root of the entire tree as skippable, but that's
	   // what we want

	   return;
	  }



	private int  New_Node  ()

	/* Allocate and return the subscript of a new node. */

	  {
	   return  Next_Avail_Node ++;
	  }


	private void  Set_Subtree_Size
	    (int Root, boolean Is_Leaf, int Parent, int Parent_Depth)
	
	//  Set the  Subtree_Size  field of  Root  and all its descendants
	//  to the number of leaves in its subtree.
	//   Is_Leaf  indicates whether  Root  is a leaf or internal node.
	//   Parent  is the parent of  Root  and  Parent_Depth  is its
	//  string-depth in the suffix tree.
	
	  {
	   int  Start, Depth;
	
	   if  (Root == NIL)
	       return;
	
	   if  (Is_Leaf)
	       {
	        Start = Leaf_Array.get(Root) . Lo - Parent_Depth;
	        if  (Leaf_Array.get(Root) . Is_Duplicate)
	            Node_Array.get(Parent) . Subtree_Size ++;
	          else
	            Node_Array.get(Parent) . Subtree_Size += 2;
	
	        Set_Subtree_Size (Leaf_Array.get(Root) . Sibling,
	               Leaf_Array.get(Root) . Sibling_Is_Leaf,
	               Parent, Parent_Depth);
	
	        return;
	       }
	
	   Depth = Parent_Depth + Node_Array.get(Root) . Len;
	
	   Set_Subtree_Size (Node_Array.get(Root) . Child, Node_Array.get(Root) . Child_Is_Leaf,
	          Root, Depth);
	
	   Set_Subtree_Size (Node_Array.get(Root) . Sibling, Node_Array.get(Root) . Sibling_Is_Leaf,
	          Parent, Parent_Depth);
	
	   if  (opt_Verbose)
	       System.out.printf ("Set_Subtree_Size:  Root = %d  Parent = %d  Parent_Depth = %d\n",
	               Root, Parent, Parent_Depth);
	
	   if  (Parent != NIL)
	       Node_Array.get(Parent) . Subtree_Size += Node_Array.get(Root) . Subtree_Size;
	
	   return;
	  }


	private void  Verify_Match
    	(int a, int b, int n, boolean reverse)

//  Verify that the match of length  n  starting at  Data [a]  and
//  Data [b]  is a maximal match.  If  reverse  is true then the
//  string at  Data [b]  goes in the reverse direction.
//  Assume  Data  is padded at the ends to prevent errors
//  caused by falling off the ends.

  {
   int  i;

   if  (reverse)
       {
        if  (Data [a - 1] == Complement (Data [b + 1]))
            System.out.printf ("Verify_Match:  a=%d  b=%d  rev=%c  preceding chars match\n",
                    a, b, 't');
        if  (Data [a + n] == Complement (Data [b - n]))
        	System.out.printf ("Verify_Match:  a=%d  b=%d  rev=%c  following chars match\n",
                    a, b, 't');
        for  (i = 0;  i < n;  i ++)
          if  (Data [a + i] != Complement (Data [b - i]))
              {
        	  System.out.printf ("Verify_Match:  a=%d  b=%d  rev=%c  mismatch at %d[%c]:%d[%c]\n",
                       a, b, 't', a + i, Data [a + i], b - i, Complement (Data [b - i]));
               System.exit (1);
              }
       }
     else
       {
        if  (Data [a - 1] == Data [b - 1])
        	System.out.printf ("Verify_Match:  a=%d  b=%d  rev=%c  preceding chars match\n",
                    a, b, 'f');
        if  (Data [a + n] == Data [b + n])
        	System.out.printf ("Verify_Match:  a=%d  b=%d  rev=%c  following chars match\n",
                    a, b, 'f');
        for  (i = 0;  i < n;  i ++)
          if  (Data [a + i] != Data [b + i])
              {
        	  System.out.printf ("Verify_Match:  a=%d  b=%d  rev=%c  mismatch at %d[%c]:%d[%c]\n",
                       a, b, 'f', a + i, Data [a + i], b + i, Data [b + i]);
        	  System.exit (1);
              }
       }

   return;
  }
	
	
	public static void main(String[] args){
		RepeatMatch rm = new RepeatMatch("ATGCGC");
		
	}
	
	public void ShowTree(){
		System.out.printf ("\n\nNodes in Entire Suffix Tree:\n\n");
		List_Tree (Tree_Root, false, 0, 0);
	}
}
