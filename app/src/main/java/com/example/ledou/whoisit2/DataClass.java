package com.example.ledou.whoisit2;

import java.util.List;

/**
 * Created by ledou on 22/11/2016.
 */

public class DataClass {




    public static int[] persoonFoto = {
            R.drawable.alexander_vandamme,
            R.drawable.angelo_lebon,
            R.drawable.anthony_baert,
            R.drawable.cedric_debot,
            R.drawable.dean_delanoye,
            R.drawable.dennis_noens,
            R.drawable.desmedt_axel,
            R.drawable.donovan_desmedt,
            R.drawable.driesmeert,
            R.drawable.elien_callens,
            R.drawable.eline_snyers,
            R.drawable.jens_bockstal,
            R.drawable.jens_leirens,
            R.drawable.jeroen_hoste,
            R.drawable.jonas_wallays,
            R.drawable.joris_boschmans,
            R.drawable.kas_dedurpel,
            R.drawable.laura_snyers,
            R.drawable.laurens_lavaert,
            R.drawable.lennert_hofman,
            R.drawable.maarten_vanmeersche,
            R.drawable.manu_schoenmakers,
            R.drawable.mathias_smet,
            R.drawable.matthias_seghers,
            R.drawable.michael_verhaeghe,
            R.drawable.milton_hooft,
            R.drawable.pieter_debusschere,
            R.drawable.pol_depuysseleyr,
            R.drawable.remko_vermaeren,
            R.drawable.robin_malfait,
            R.drawable.robin_van_den_broeck,
            R.drawable.silke_venneman,
            R.drawable.sofie_delaeter,
            R.drawable.sofie_deplus,
            R.drawable.thomas_ledoux,
            R.drawable.thomas_lefevere,
            R.drawable.tom_vandenbussche,
            R.drawable.toon_de_true,
            R.drawable.xander_berkein,
            R.drawable.yannick_vanhecke,
            R.drawable.yoran_roels
    };


    public static String[] persoonNamen = {
            "Alexander Vandamme"
    ,"Angelo Lebon",
    "Antohony Baert",
    "Cedric Debot",
    "Dean Delanoye",
            "Dennis Noens",
            "Axel Desmedt",
            "Donovan Desmedt",
            "Dries Meert",
            "Elien Callens",
            "Eline Snyers",
            "Jens Bockstal",
            "Jens Leirens",
            "Jeroen Hoste",
            "Jonas Wallays",
            "Joris Boschmans",
            "Kas De Durpel",
            "Laura Snyers",
            "Laurens Lavaert",
            "Lennert Hofman",
            "Maarten Vanmeersche",
            "Manu Schoenmakers",
            "Mathias Smet",
            "Matthias Seghers",
            "Michael Verhaeghe",
            "Milton Hooft",
            "Pieter Debusschere",
            "Pol De puysseleyr",
            "Remko Vermaeren",
            "Robin Malfait",
            "Robin Van Den Broeck",
            "Silke Venneman",
            "Sofie Delaeter",
            "Sofie Deplus",
            "Thomas Ledoux",
            "Thomas Lefevere",
            "Tom Vandenbussche",
            "Toon De True",
            "Xander Berkein",
            "Yannick Vanhecke",
            "Yoran Roels"
    };

    public static String[] persoonDetails = {
            "Van Damme\n" +
                    "Alexander\n" +
                    "Chicks aantrekken\n" +
                    "Computernetwerken I\n" +
                    "Java\n" +
                    "22",
            "Naam: Lebon Angelo\n" +
                    "Hobbies: Watersporten / Lopen\n" +
                    "Minst favoriete vak: Analyse\n" +
                    "Favoriete taal: Java\n" +
                    "Leeftijd: 21 jaar",
            "Dit is Anthony. Anthony is hier 5 min verwijderd van de 1ste fase van ontkenning van te veel drinken. Vanaf hier ging het alleen maar bergaf voor Anthony.",
            "Naam : Debot\n" +
                    "Voornaam : Cédric\n" +
                    "Hobby: DJ\n" +
                    "Minst populaire opleidingsonderdeel TIN : Italent\n" +
                    "Favoriete programmeertaal: Swift\n" +
                    "Leeftijd 20\n",
            "Delanoye\n" +
                    "Dean\n" +
                    "Voetballen\n" +
                    "Databanken\n" +
                    "Swift\n" +
                    "24jaar\n",
            "Noens\n" +
                    "Dennis\n" +
                    "Fotografie\n" +
                    "Computernetwerken I\n" +
                    "Java\n" +
                    "23 jaar\n",
            "De Smedt\n" +
                    "Donovan\n" +
                    "Tennis\n" +
                    "iTalent\n" +
                    "Javascript\n" +
                    "20 jaar\n",
            "Meert\n" +
                    "Dries\n" +
                    "Skateboarding en speelplein\n" +
                    "iTalent\n" +
                    "Java\n" +
                    "25 jaar\n",
            "Callens\n" +
                    "Elien\n" +
                    "Reizen\n" +
                    "Computernetwerken I\n" +
                    "Java\n" +
                    "24 jaar\n",
            "Snyers\n" +
                    "Eline\n" +
                    "Tennis\n" +
                    "Webapplicaties\n" +
                    "Swift\n" +
                    "25 jaar\n",
            "Bockstal\n" +
                    "Jens\n" +
                    "Gezin, technologie\n" +
                    "Internationale Communicatie\n" +
                    "Java\n" +
                    "31 jaar\n",
            "Leirens\n" +
                    "Jens\n" +
                    "Gamen\n" +
                    "Bedrijfsmanagement\n" +
                    "Java\n" +
                    "20 jaar\n",
            "Hoste\n" +
                    "Jeroen\n" +
                    "Boardgames\n" +
                    "iTalent\n" +
                    "Java\n" +
                    "23 jaar\n",
            "Wallays\n" +
                    "Jonas\n" +
                    "Badminton\n" +
                    "Partim Topics\n" +
                    "C#\n" +
                    "21 jaar\n",
            "Boschmans\n" +
                    "Joris\n" +
                    "Gitaar\n" +
                    "Financieel Management\n" +
                    "C#\n" +
                    "24 jaar\n",
            "De Durpel\n" +
                    "Kas\n" +
                    "Volleybal\n" +
                    "Computernetwerken\n" +
                    "Java\n" +
                    "23 jaar\n",
            "Snyers\n" +
                    "Laura\n" +
                    "Viool, tennis, bergwandelen\n" +
                    "iTalent\n" +
                    "Java\n" +
                    "20 jaar\n",
            "Lavaert\n" +
                    "Laurens\n" +
                    "Programmeren\n" +
                    "Windows Server\n" +
                    "Javascript\n" +
                    "21 jaar\n",
            "Lemenu\n" +
                    "Bram\n" +
                    "Lopen\n" +
                    "iTalent\n" +
                    "Swift\n" +
                    "21 jaar\n",
            "Hofman\n" +
                    "Lennert\n" +
                    "Badminton\n" +
                    "iTalent\n" +
                    "Java\n" +
                    "19 jaar\n",
            "Van Meersche\n" +
                    "Maarten\n" +
                    "Gamen\n" +
                    "iTalent\n" +
                    "Java\n" +
                    "19 jaar\n",
            "Schoenmakers\n" +
                    "Manu\n" +
                    "Gitaar\n" +
                    "iTalent\n" +
                    "Java\n" +
                    "20 jaar\n",
            "Smet\n" +
                    "Mathias\n" +
                    "Voetbal\n" +
                    "Databanken\n" +
                    "Java\n" +
                    "23 jaar\n",
            "Seghers\n" +
                    "Matthias\n" +
                    "Tennis, fitness, lopen\n" +
                    "iTalent, Topics\n" +
                    "C#\n" +
                    "19 jaar\n",
            "Verhaeghe\n" +
                    "Michael\n" +
                    "Weerkunde\n" +
                    "Financieel Management\n" +
                    "Python\n" +
                    "23 jaar\n",
           "Hooft\n" +
                    "Milton\n" +
                    "Boogschieten, elektrische gitaar\n" +
                    "Internationale Communicatie\n" +
                    "Java\n" +
                    "21 jaar\n",
            "De Busschere\n" +
                    "Pieter\n" +
                    "Muziek\n" +
                    "iTalent\n" +
                    "Java\n" +
                    "21 jaar\n",
            "De Puysseleyr\n" +
                    "Pol\n" +
                    "Biljart\n" +
                    "iTalent\n" +
                    "Java\n" +
                    "21 jaar\n",
            "Vermaeren\n" +
                    "Remko\n" +
                    "Lopen\n" +
                    "iTalent\n" +
                    "Java\n" +
                    "21 jaar\n",
            "Malfait\n" +
                    "Robin\n" +
                    "Programmeren, series, eten\n" +
                    "Bedrijfsmanagement\n" +
                    "JavaScript\n" +
                    "21 jaar\n",
            "Van den Broeck\n" +
                    "Robin\n" +
                    "Squash, lopen, fietsen\n" +
                    "iTalent\n" +
                    "Sed\n" +
                    "22 jaar\n",
            "Venneman\n" +
                    "Silke\n" +
                    "Tennis\n" +
                    "Partim Topics\n" +
                    "Java\n" +
                    "23 jaar\n",
            "Delaeter\n" +
                    "Sofie\n" +
                    "Gamen, Hermes\n" +
                    "Bedrijfsmanagement\n" +
                    "Java\n" +
                    "23 jaar\n",
            "De Plus\n" +
                    "Sofie\n" +
                    "Voetbal\n" +
                    "Analyse\n" +
                    "Java\n" +
                    "21 jaar\n",
            "Ledoux\n" +
                    "Thomas\n" +
                    "Korfbal\n" +
                    "Databanken\n" +
                    "Java\n" +
                    "25 jaar\n",
            "Lefevre\n" +
                    "Thomas\n" +
                    "Piano, vliegen, uitgaan\n" +
                    "Databanken\n" +
                    "C#\n" +
                    "20 jaar\n",
            "Vandenbussche\n" +
                    "Tom\n" +
                    "Games\n" +
                    "iTalent\n" +
                    "Java\n" +
                    "25 jaar\n",
            "De True\n" +
                    "Toon\n" +
                    "Schaken\n" +
                    "iTalent\n" +
                    "C#\n" +
                    "24 jaar\n",
            "Berkein\n" +
                    "Xander\n" +
                    "Toneel\n" +
                    "Internationale Communicatie\n" +
                    "Java\n" +
                    "22 jaar\n",
            "Van Hecke\n" +
                    "Yannick\n" +
                    "Badminton\n" +
                    "iTalent\n" +
                    "C#\n" +
                    "23 jaar\n",
            "Roels\n" +
                    "Joran\n" +
                    "Gaming\n" +
                    "Internationale Communicatie\n" +
                    "Java\n" +
                    "20 jaar\n"

    };
}
