package quest;

/*
*
På dagens handledning dök det upp en fråga som kan vara av intresse för fler:
* hur implementerar man testfall för till exempel en tillståndsmaskin?
* Som vanligt finns det inte ett universellt svar på denna fråga, utan svaret beror på många saker,
* inte minst hur tillståndsmaskinen själv är implementerad, och hur man styr den.

En variant som kan vara lämplig för många grupper på INTE är att bygga upp testfallet något i den här stilen:

@Test
@DisplayName("State machine test #1: purchase ...")
void purchaseWithoutAnyProblems(){
    // State A - start state
    kod för att sätta upp startillståndet

    // Edge 1 - first item purchased
    purchase.add(anyItem);

    // State B - adding items
    purchase.add(anyItem);
    purchase.add(anyItem);
    purchase.add(anyItem);

    // ...

}
Kommentarerna gör det lätt att följa hur testet rör sig genom maskinen.
* */

/*
* Tillståndsmaskin för Quest:
* Ett quest ska kunna tillhöra en NPC.
* Ett quest ska kunna accepteras av en spelare genom att interagera med en NPC.
* Ett quest kan i nuvarande form vara en av två olika typer av quest: ett killQuest eller ett talkQuest
* Ett quest ska kunna ha olika states, så som notInitiated, initiated, notCompleted, completed, notReturned och returned
* Ett quest ska kunna uppdateras i status beroende på om questmålet har nåtts eller delvis nåtts*/
public class QuestTestStateMachine {
}
