// simple check to only allow s1@s2.s3 where s1, s2, s3 don't include @ symbol
export class EmailValidator {
    isValidEmail(email: string) {
        return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    }
}