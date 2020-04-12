interface StringUtil {
    isValidity (str: string, validCondition?: { minLength?: number, maxLength?: number, mustNotInStr? : string, mustInStr? : string}): boolean
}