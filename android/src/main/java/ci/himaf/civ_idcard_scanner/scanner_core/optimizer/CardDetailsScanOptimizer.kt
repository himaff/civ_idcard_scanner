package ci.himaf.civ_idcard_scanner.scanner_core.optimizer

import android.util.Log
import ci.himaf.civ_idcard_scanner.logger.debugLog
import ci.himaf.civ_idcard_scanner.scanner_core.models.CardDetails
import ci.himaf.civ_idcard_scanner.scanner_core.models.CardScannerOptions

class CardDetailsScanOptimizer(private val _scannerOptions: CardScannerOptions) {
  private val _expiryDateFrequency: MutableMap<String, Int> = mutableMapOf()
  private val _cardNumberFrequency: MutableMap<String, Int> = mutableMapOf()
  private val _cardFirstNameFrequency: MutableMap<String, Int> = mutableMapOf()
  private val _cardLastNameFrequency: MutableMap<String, Int> = mutableMapOf()
  private var _optimalCardNumber: String? = null
  private var _optimalExpiryDate: String? = null
  private var _optimalCardFirstName: String? = null
  private var _optimalCardLastName: String? = null
  private var numberOfCardDetailsProcessed: Int = 0;

  fun processCardDetails(cardDetails: CardDetails) {
    if (cardDetails.cardNumber.isEmpty()) return;
    val cardNumber = cardDetails.cardNumber
    val cardFirstName = cardDetails.cardFirstName;
    val cardLastName = cardDetails.cardLastName;
    val expiryDate = cardDetails.expiryDate
    numberOfCardDetailsProcessed++;

    ///drop first few scan results which have more chances of errors
    if (numberOfCardDetailsProcessed <= _scannerOptions.initialScansToDrop) return;
    _handleCardNumber(cardNumber);
    _handleCardFirstName(cardFirstName);
    _handleCardLastName(cardLastName);
    _handleExpiryDateNumber(expiryDate);
    _updateOptimalData();
  }

  fun isReadyToFinishScan(): Boolean {
    return numberOfCardDetailsProcessed > _scannerOptions.validCardsToScanBeforeFinishingScan;
  }

  private fun _updateOptimalData() {
    _optimalCardNumber = _getMostFrequentData(_cardNumberFrequency);
    _optimalExpiryDate = _getMostFrequentData(_expiryDateFrequency);
    _optimalCardFirstName = _getMostFrequentData(_cardFirstNameFrequency);
    _optimalCardLastName = _getMostFrequentData(_cardLastNameFrequency);
  }

  private fun _handleCardNumber(cardNumber: String) {
    if (cardNumber.isEmpty()) return;
    _cardNumberFrequency[cardNumber] = (_cardNumberFrequency[cardNumber] ?: 0) + 1
  }

  private fun _handleExpiryDateNumber(expiryDate: String) {
    if (expiryDate.isEmpty()) return;
    _expiryDateFrequency[expiryDate] = (_expiryDateFrequency[expiryDate] ?: 0) + 1
  }

  private fun _handleCardFirstName(cardHolderName: String) {
    if (cardHolderName.isEmpty()) return;
    _cardFirstNameFrequency[cardHolderName] = (_cardFirstNameFrequency[cardHolderName] ?: 0) + 1
  }

  private fun _handleCardLastName(cardHolderName: String) {
    if (cardHolderName.isEmpty()) return;
    _cardLastNameFrequency[cardHolderName] = (_cardLastNameFrequency[cardHolderName] ?: 0) + 1
  }

  private fun _getMostFrequentData(frequencyMap: MutableMap<String, Int>): String? {
    var mostFrequentEntry: Map.Entry<String, Int>? = null
    for (entry in frequencyMap.entries) {
      if (mostFrequentEntry == null || entry.value >= mostFrequentEntry.value) {
        mostFrequentEntry = entry;
      }
    }
    return mostFrequentEntry?.key;
  }

  fun printStatus() {
    debugLog(" card number : " + _cardNumberFrequency[_optimalCardNumber
            ?: ""] + " , expiry = " + _expiryDateFrequency[_optimalExpiryDate
      ?: ""] + " , holder name = " + _cardLastNameFrequency[_optimalCardLastName] + _cardFirstNameFrequency[_optimalCardFirstName], _scannerOptions);
  }

  fun getOptimalCardDetails(): CardDetails? {
    if (_optimalCardNumber == null) return null;
    printStatus()
    return CardDetails(cardNumber = _optimalCardNumber!!, cardFirstName = _optimalCardFirstName
            ?: "", cardLastName = _optimalCardLastName ?: "",
            expiryDate = _optimalExpiryDate ?: "");
  }
}