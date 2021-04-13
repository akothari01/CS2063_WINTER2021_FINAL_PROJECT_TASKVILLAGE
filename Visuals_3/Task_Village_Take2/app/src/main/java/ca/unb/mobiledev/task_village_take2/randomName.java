package ca.unb.mobiledev.task_village_take2;

import java.util.*;


public class randomName{

    public static String randomName(){
         
        String[] firstNames = new String[]{
            "Anya", "Arcadia", "Ariadne", "Aurelia", "Aurelian", "Avalon", 
            "Bastian", "Breena", "Beorn", "Brielle", "Cambria", "Cara", "Carys", "Caspian", "Cassiel",
            "Cassiopeia", "Cassius", "Chaniel", "Cora", "Corbin", "Cyprian", "Daire", "Darius", "Destin",
            "Drake", "Drystan", "Eira", "Eirian", "Elysia", "Eoin", "Evadne", "Fineas", "Finian", "Fyodor",
            "Gareth", "Gavriel", "Griffin", "Guinevere", "Hadriel", "Hannelore", "Hermione", "Hesperos", "Iagan", "Ianthe",
            "Ignacia", "Ignatius", "Iseult", "Isolde", "Jessalyn", "Kara", "Kerensa", "Korbin", "Kyler", "Kyra", "Leala", "Leila",
            "Lilith", "Liora", "Lucien", "Lyra", "Maia", "Marius", "Mathieu", "Mireille", "Mireya", "Natania", "Nerys", "Nuriel",
            "Nyssa", "Oisin", "Oralie", "Orion", "Orpheus", "Ozara", "Peregrine", "Persephone", "Perseus", "Petronela", "Phelan", "Qadira",
            "Quintessa", "Raisa", "Remus", "Rhyan", "Rhydderch", "Riona", "Saoirse", "Sarai", "Sebastian", "Seraphim", "Tavish", "Tearlach", "Terra", "Thalia",
            "Thaniel", "Theia", "Torian", "Torin", "Tressa", "Tristana", "Uriela", "Urien", "Vanora", "Vespera", "Xanthus", "Yadira", "Yseult", "Zaira", "Zephyr",
            "Zora", "Zoiron", "Acalia", "Briallian"       
        
        
        };

       String[] lastNames = new String[]{
            "Ambers", "Andilet", "Albion", "Autumnbow", "Ashsorrow", "Abbington", "Albimbert", "Ambrose", "Astaseul", "Abril", "Albania", "Albizia", "Amberflaw",
            "Abordieu", "Alinac", "Amberflayer", "Amberhide", "Andre", "Asteria", "Bizeveron", "Belmont", "Brichazac", "Blackmark", "Bellevue", "Broffet", "Bougaitelet", "Bluebleeder", "Boneflare", 
            "Bobellon", "Brichallard", "Bougaimoux", "Bonnie", "Barleyjumper", "Brightdoom", "Brownie", "Bertillon",
            "Crowstrike", "Coldblight", "Cinderbreaker", "Crestbreeze", "Cinderhell", "Crestbreeze", "Clanwing", "Chabarares", "Clanwillow", "Castemont", "Clearpunch",
            "Credieu", "Distantfury", "Duras", "Dawnless", "Dumieres", "Duskbloom", "Dustseeker", "Duststalker", "Deepwing", "Dragoncutter",
            "Echethier", "Elfscribe", "Embershadow", "Elffire", "Elfbreath", "NotADeerInAHumanCostume", "Flatwatcher", "Frozenscribe", "Finecrusher", "Frozenreaper", "Flameshaper",
            "Featherbrew", "Fourspire", "Flame", "Ginerisey", "Gorelight", "Grandsplitter", "Grasshammer", "Ginemoux", "Gairil", "Gloryrock", "Gaimbert", "Gaignory", "Gaillot",
            "Hardshout", "Humblebringer", "Havendoom", "Hydrabreath", "Hallowedchaser", "Humblereaper", "Hardarm", "Humblecut", "Hallowswift",
            "Irongrip", "Icehand", "Ironcut", "Jouvempes", "Jouvessac", "Keenstone", "Keenfollower", "Lomadieu", "Larmalleve", "Lamagnon", "Laughingroar", "Larmanton", "Laurengnory", "Larmagnory", "Leadslayer", "Lamadras",
            "Lignichanteau", "Lamadras", "Larmalart", "Lamogre", "Mildbreath", "Marblemaw", "Mountainbane", "Masterfang", "Macherac", "Mirthmantle", "Maignes", "Montarac", "Marbletail", "Neredras", "Noblestrike", "Nicklegrain", "Nicklewhisk",
            "Nobledane", "Nobledrifter", "Oattaker", "Orbarrow", "Oceancut", "Oatcrag", "Orbstrike", "Peacecream", "Pellerelli", "Proudswift", "Pridesong", "Pridewood", "Roughwhirl", "Regalhelm", "Redshadow", "Roserun", "Rosedreamer", "Ronchelieu", "Roqueze",
            "Rapidroot", "Rochegne", "Rainward", "Silverweaver", "Softgloom", "Slateflayer", "Shadegrove", "Springbender", "Strongblaze", "Silentbrace", "Skysnow", "Sternshine", "Saurmaw", "Skyshade", "Shadowflaw", "Sagesun", "Sternguard",
            "Sufelon", "Skyhunter", "Stoutspirit", "Terrarock", "Terramaul", "Terraspear", "Terrawater", "Tusksnarl", "Treeshaper", "Vernize", "Voidreaper", "Vernillard", "Vernifelon", "Vassezac", "Verninne", "Vegne", "Virac",
            "Voidlash", "Voidbend", "Warmane", "Winterwound", "Wolfgrain", "Wheatbrow", "Warbreaker", "Woodlight", "Wisekiller", "Woodflower", "Wyvernseeker", "Youngvigor"
       };

       String titles[] = new String[]{

            "Keeper of Bees", "Necromancer", "Wizard of the Forest", "Wizard of the Elements", "Keeper of Horses", "Keeper of Dogs", "Keeper of Cats", "Keeper of Ants",
            "Wizard of Time", "Bad Chef", "Good Chef", "Eater of Food", "Janitor", "Wizard of Janitorial Duties", "Finder of Lost Animals", "Finder of Lost Lunches", "Pirate", "Name Assigner", "Spelcheker", "Puncher of Faces", "Hater of Cats", "Hater of Dogs", "Hater of Haters", "Hater of Clowns", "The Clown",
            "The Farmer", "The Teacher", "The Chosen One", "The Other Chosen One", "Another Chosen One", "Saviour of Seats"

       };

       boolean hasTitle;
       double titleCheck = Math.random();
       if(titleCheck >= 0.7){
           hasTitle = true;
       }
       else{
           hasTitle = false;
       }

       String name = firstNames[(int)(Math.random()*firstNames.length)] + " " + lastNames[(int)(Math.random()*lastNames.length)];

       if(hasTitle){
            name += " " + titles[(int)(Math.random()*titles.length)];
       }

       

       

       return name;

       
       
           
        
    }



}