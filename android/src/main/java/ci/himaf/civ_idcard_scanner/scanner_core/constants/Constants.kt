package ci.himaf.civ_idcard_scanner.scanner_core.constants

abstract class CardScannerRegexps {
  companion object {
      val cardNumberRegex = "CI([0-9]{9})";
      val expiryDateRegex = "(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/(20[2-9][0-9])";
      val cardFirstName = "^ *(([A-Z.]+ {0,2}){1,8}) *\$"; // A line containing name has : minimum 1 word and maximum 8 words
      val cardLastName = "^ *(([A-Z.]+ {0,2}){1}) *\$";

  }
}


abstract class CardHolderNameConstants {
  companion object {
    val defaultBlackListedWords =
            setOf<String>(
                    "REPUBLIQUE DE COTE D'IVOIRE",
                    "CARTE NATIONALE D'IDENTITE")
  };
}