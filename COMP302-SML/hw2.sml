(*
Name:         Martin Weiss
Student ID:   260405962 

   COMP 302 Winter 2013 HOMEWORK 2
   Instructors: Francisco Ferreira, Andrew Cave
   Due 1st February 2013, at the BEGINNING OF CLASS (2:35pm)*)



(* Question 2: Associative lists *)

(* Question 2.1 *)
fun zipWith f xs ys = ListPair.foldr (fn(a,b,cs) => f(a,b)::cs) [] (xs,ys);
zipWith (fn(x,y) => x+y) [1,2,3] [10,20,30,40];

(* Question 2.2 *)
fun ziprev xs ys = List.rev(zipWith (fn(x,y) =>x+y) xs ys);
ziprev [1,2,3,4,5] [10,20,30,40];

(* Question 2.3 *)

fun lookup (k:string) [] = NONE
|lookup (k:string) ((x:string,y:int)::xs) = if k=x then SOME y else lookup k xs;


(* Question 2.4 *)
type dict = string -> int option

val empty : dict = fn key => NONE

fun insert (key:string, value:int) d = fn s => if s = key then SOME value else d s;
fun lookup2 k d = d k

val d = insert ("foo",2) (insert ("bar",3) empty);
lookup2 "foo" d;
lookup2 "bar" d;
lookup2 "baz" d;



(* Question 3 preamble *)

(* Suspended computation : we can suspend computation
   by wrapping it in a closure. *)
datatype 'a susp = Susp of (unit -> 'a)

(* delay: *)
fun delay (f ) = Susp(f)

(* force: *)
fun force (Susp(f)) = f ()

(* ---------------------------------------------------- *)
(* Define an infinite stream *)
datatype 'a stream' = Cons of 'a  * 'a stream
withtype 'a stream = ('a stream') susp

(* ---------------------------------------------------- *)
(* Inspect a stream up to n elements 
   take : int -> 'a stream' susp -> 'a list
   take': int -> 'a stream' -> 'a list
*)
fun take 0 s = []
  | take n (s) = take' n (force s)
and take' 0 s = []
  | take' n (Cons(x,xs)) = x::(take (n-1) xs)

(* Question 3.1 *)

fun Sequence(x:int) = Susp(fn () => Cons(x, Sequence (x)));

take 10 (Sequence (3));

fun hailstones n = if n mod 2 = 0 then Susp(fn() => Cons(n, hailstones (n div 2))) 
else Susp(fn() => Cons(n, hailstones(3*n+1)));


(* Examples:*)

take 5 (hailstones 13);
(*val it = [13,40,20,10,5,16,8,4,2,1,4,2,...] : int list*)

take 20 (hailstones 8);
(*val it = [8,4,2,1,4,2,1,4,2,1,4,2,...] : int list*)



(* Question 3.2 *)

fun unfold f x = Susp(fn () => Cons(x, unfold f (f x)))

fun deriv f x = let val h = 0.0000001 in (f (x + h) - f x) / h end

fun newton f n = unfold (fn x => x - f(x)/deriv f x) n;

(* Example: *)

take 10 (newton (fn x => x*x - 2.0) 7.0);

val it =
  [7.0,3.64285718209,2.09593841013,1.52508247335,1.41824348423,1.41421928801,
   1.41421356238,1.41421356237,1.41421356237,1.41421356237] : real list


