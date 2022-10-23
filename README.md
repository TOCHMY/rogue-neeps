# rogue-neeps


De grå är obligatoriska, de gula ska vi välja minst 4 utav och göra
![image](https://user-images.githubusercontent.com/49158143/194512325-deea8a98-64a1-43c6-b9a2-ac91ffaef681.png)

Förslag: 

# Idéer 

## Spelare: 
Färdigheter: 
- Strength
- Dexterity
- Intelligence

Klasser:
Fighter
Archer
Mage


## NPCer:
Comment för att komma ihåg senare: interface för movement?
### Snälla npc.NPC

### Elaka npc.NPC

## Magi: 
- Items har sockets
- Olika färg på sockets
- Gemstones med olika färger
- Olika färgade gemstones har olika typer av magier
- Tex: Röd gemstone har offensiva magier, Gul gemstone har defensiva magier, Blå gemstones har passiva eller nåt 
 
 ### Exempel på magier
 RödGem1 = Ger 10% starkare slag 
 RödGem2 = Ger 5% snabbare attack speed
 
 GulGem1 = Ger 10% mer HP 
 GulGem2 = Ger 10% mer ARmor
 
 BlåGem1 = Ger 3% mer XP 
 
 LilaGem1 = Ger spelaren en magi-attack som kastar en eldboll som gör X skada och kostar Y mana 

## Utrustning: 
 - Items har sockets, olika mängd (random?)
 - Sockets kan ha olika färger (random?)
 - Kan ges som belöning
 - Kan hittas i typ kistor
 - Kan droppas från fiender
 - Styrka baserad på spelarens level? 
 - Olika datasets? I don't know List1(levelOneItems) List2(levelTwoItems) Svärd1 -> List1, MycketStarkareSvärd2 -> List2
 - Förslag 1: Level på items påverkar magi-gemstone-krat
 

## FIGHT: 
- npc.Pig is in x=50, y=50; 
- player.strike(x=50, y=50);
- npc.Pig.die(); // give xp osv kanske i dont know 

## KARTA: 
- Förslag: Kartan är 100x100 pixlar. Spelare och NPCer rör sig genom att hålla koll på position med hjälp av arrayer. 
 - Så om npc.NPC Randy skapas på position x=50 y=50 och sedan rör sig upp ett steg så är Randys nya position x=50, y=51. 
 
 ## TODO: 
 Tommy ska hitta på en massa roliga magier
 Tommy ska också skriva en massa roliga quest och designa questen 



## LEONS TANKAR: 

Jag tänker att klasser som i wow annars kan skapa ganska mycket olika testfall
t.ex. att man har en klass som ärver nå interface, t.ex. One-hand, dual wield, staff, alltså, vilket vapen man får hålla. 
Sen har vissa klasser mana för de ärver från nå mage interface. 
Sen nå combat system där man slår på varandra och det dras HP baserat på attackstyrka, rng.nr 1.05% dmg etc.
Det jag menar är att jag tänker på förhand att uppgiften skulle kretsa mycket kring ärvning av klasser för att skapa olika ingame klasser. Och typ combat systemet

## Item-systemet:
Det finns olika typer av Items
### Items har:
- Styrka mellan 1-100
- Mellan 1 och 5 sockets av olika MagicColors
Varje MagicSocket har:
-  En MagicColor. 
- En plats för en GemStone av samma MagicColor som sig själv 
GemStones har:
-  En MagicColor 
- Strength (int mellan 1-30)
- Cost (int mellan 1-30)

### MagicColors som hittills finns är:
- BLUE(Attack)
- RED(Defence)
- PURPLE(Magic)
- GREEN(Intelligence)

### Items som hittills finns:
- Weapon
	- Kan användas för attack och defence, kan därmed ha BLUE och RED sockets
- Armor
	- Kan användas för defence, kan därmed ha RED sockets 
	- EV. TILLÄGG (Man blir klumpig och får mindre dexterity)
- Shield
	- Kan användas för defence, kan därmed ha RED sockets
- EVENTUELLT. HELMET
	- Med defence och intelligence
- MagicBag
	- Kan innehålla PURPLE sockets som används för spells

### En Player har en ItemCollection
	ItemCollection kan vara tom eller innehålla
- Upp till 2 handhållna items, dessa kan vara:
    - Upp till 2 Vapen
    - Max 1 sköld 
- 1 Armor
- 1 Shield
- EVENTUELLT. 1 Helmet
- I nuläget 1 MagicBag, den ska bort härifrån
Man kan lägga till och ta bort Items från ItemCollection

Items strength påverkas 

När spelaren gör attack eller försvar 
