package ci.himaf.civ_idcard_scanner

import com.google.mlkit.vision.text.Text
import ci.himaf.civ_idcard_scanner.scanner_core.models.CardDetails
import ci.himaf.civ_idcard_scanner.scanner_core.models.CardScannerOptions
import ci.himaf.civ_idcard_scanner.scanner_core.scan_filters.CardLastNameFilter
import ci.himaf.civ_idcard_scanner.scanner_core.scan_filters.CardFirstNameFilter
import ci.himaf.civ_idcard_scanner.scanner_core.scan_filters.CardNumberFilter
import ci.himaf.civ_idcard_scanner.scanner_core.scan_filters.ExpiryDateFilter
import ci.himaf.civ_idcard_scanner.logger.debugLog

class SingleFrameCardScanner(private val scannerOptions: CardScannerOptions) {
  fun scanSingleFrame(visionText: Text): CardDetails? {
    val cardNumberResult = CardNumberFilter(visionText, scannerOptions).filter();
    if (cardNumberResult?.cardNumber?.isEmpty() != false) {
      return null;
    }
    val cardFirstNameResult = CardFirstNameFilter(visionText, scannerOptions, cardNumberResult).filter();
    val cardLastNameResult = CardLastNameFilter(visionText, scannerOptions, cardNumberResult).filter();
    val cardExpiryResult = ExpiryDateFilter(visionText, scannerOptions, cardNumberResult).filter();

    return CardDetails(
      cardNumber = cardNumberResult.cardNumber,
      cardFirstName = cardFirstNameResult?.cardFirstName
        ?: "",
      cardLastName = cardLastNameResult?.cardLastName
        ?: "",
      expiryDate = cardExpiryResult?.expiryDate
            ?: ""
    );
  }
}